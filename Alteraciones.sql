use puntodeventa;

alter table sucursal

add foreign key (idMunicipio) references municipio(idMunicipio);

delete from empresa where idEmpresa<>1;
select * from empresa;

insert into empresa values(null,'Abarrotes Pancho',LOAD_FILE('C:\Users\kevin\Documents\abarrotes pancho.jpg'),LOAD_FILE('C:\Users\kevin\Documents\imagen.jpg'),'Lo que buscas lo encuentras');
select * from empresa;
insert usuario values(null, 'Kevin','kevin@hotmail.com','kevin','Tamez','DE La Fuente',1,'FUTK940913HNLNMV07',null,1,2,'El cercado', 67323,'Eleuterio Gonzales',208,1994-09-13,LOAD_FILE('C:\Users\kevin\Documents\kevin.jpg'),1);

