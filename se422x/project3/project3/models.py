from django.db import models
from storages.backends.s3boto3 import S3Boto3Storage

# Create your models here.

class s3Storage(S3Boto3Storage):
	location = 'photos/'

class Photo(models.Model):
	id = models.BigAutoField(primary_key=True)
	image = models.ImageField(upload_to='photos/', storage=s3Storage())
	name = models.CharField(max_length=255)

