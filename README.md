Base de datos 

Create table usuario(
usuario varchar2(50) not null,
contraseña varchar2(50)
);

Create table Ticket (
uuid Varchar2(50),
Numero_Ticket int,
Titulo_Ticket varchar2(50),
Descripion_Ticket varchar2(50),
Autor_Ticket varchar2(50),
Email_Autor varchar2(50),
Estado_Ticket varchar(50)
); 
