
docker container run -d -e DATABASE_HOST=hiseuibd01.upsa.local -e DATABASE_PORT=1521 -e DATABASE_SID=alumnos -e DATABASE_USER=alumno2010 -e DATABASE_PWD=alumno2010 -p 80:8080 --name backend --network oracle-network backend
