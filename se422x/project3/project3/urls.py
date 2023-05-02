from django.contrib import admin
from django.contrib.auth import views as auth_views
from django.urls import path, include
from . import views

urlpatterns = [
    path('admin/', admin.site.urls),
    path('upload/', views.upload_image, name='upload_image'),
    path('process/<int:photo_id>/<str:size>/', views.process_image, name='process_image'),
    path('register/', views.registration_view, name='register'),
    path('login/', views.login_view, name='login'),
    path('logout/', views.logout_view, name='logout'),
    path('/', views.login_view, name='root'),
    path('', views.login_view, name='root'),
    path('search/', views.search_images, name='search_images'),
    path('image/<int:pk>/', views.view_image, name='view_image'),
    path('accounts/', include('django.contrib.auth.urls')),
]


