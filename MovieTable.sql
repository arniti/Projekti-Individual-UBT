use Movies

create table movies 
(
	Movie_ID int primary key identity(1,1),
	[Movie Description] varchar(100) not null
)

select * from movies