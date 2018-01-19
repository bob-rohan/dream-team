docker-machine ls
@FOR /f "tokens=*" %%i IN ('docker-machine env default') DO %%i
cd dreamteam-web
docker build -t bobrohan/dreamteam-web:latest .