from io import BytesIO
from django.shortcuts import render, redirect, get_object_or_404
from .models import Photo
from PIL import Image
from django.conf import settings
from django.contrib.auth import authenticate, login, logout
from django.contrib.auth.views import LoginView, LogoutView
from django.contrib import messages
from .forms import UserLoginForm, UserRegistrationForm
from django.views.decorators.csrf import csrf_exempt, csrf_protect, ensure_csrf_cookie
from django.core.files.storage import default_storage
from django.core.files.base import ContentFile
from django.db.models import Q

# Create your views here.

def upload_image(request):
	if not request.user.is_authenticated:
		return redirect('login')
	if request.method == 'POST':
		image = request.FILES['image']
		size = request.POST.get('size', '100')
		name = image.name
		photo = Photo.objects.create(image=image, name=name)
		photo.save()

		return redirect('process_image', photo_id=photo.id, size=size)

	return render(request, 'upload.html')

def process_image(request, photo_id, size):
    #photo = Photo.objects.get(id=photo_id)
    #img = Image.open(default_storage.open(photo.image.name))
    #img = img.resize((int(size), int(size)))
    #img.save(photo.image.path)

    #return render(request, 'result.html', {'processed_image': photo.image})

    photo = Photo.objects.get(id=photo_id)
    img = Image.open(photo.image)
    img = img.resize((int(size), int(size)))
    thumb_io = BytesIO()
    img = img.convert('RGB')
    img.save(thumb_io, format='JPEG')
    thumb_file = ContentFile(thumb_io.getvalue())
    photo.image.save(f'{size}x{size}.jpg', thumb_file, save=False)
    photo.save()
    return redirect('view_image', pk=photo.pk)

@csrf_exempt
def login_view(request):
    if request.method == 'POST':
        form = UserLoginForm(request.POST)
        if form.is_valid():
            username = form.cleaned_data['username']
            password = form.cleaned_data['password']
            user = authenticate(username=username, password=password)
            if user is not None:
                login(request, user)
                request.session['username'] = username
                return redirect('upload_image')
            else:
                messages.error(request, 'Invalid login credentials')
    else:
        form = UserLoginForm()

    return render(request, 'registration/login.html', {'form': form})

def logout_view(request):
    logout(request)
    return redirect('login')

def registration_view(request):
    if request.method == 'POST':
        form = UserRegistrationForm(request.POST)
        if form.is_valid():
            user = form.save()
            login(request, user)
            return redirect('upload_image')
    else:
        form = UserRegistrationForm()

    return render(request, 'register.html', {'form': form})

def search_images(request):
    query = request.GET.get('q')
    if query:
        photos = Photo.objects.filter(name__icontains=query)
    else:
        photos = Photo.objects.all()
    context = {'photos': photos, 'query': query}
    return render(request, 'search.html', context)

def view_image(request, pk):
    photo = Photo.objects.get(pk=pk)
    return render(request, 'photo_detail.html', {'photo': photo})

class MyLoginView(LoginView):
    template_name = "project3/templates/registration/login.html"
    success_url = "/upload-image/"

class MyLogoutView(LogoutView):
    next_page = "/"

