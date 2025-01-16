insert into users(id, email, password, name, role, created_at) values
(1,'admin@gmail.com','$2a$10$hKDVYxLefVHV/vtuPhWD3OigtRyOykRLDdUAp80Z1crSoS1lFqaFS','Administrator', 'ROLE_ADMIN', CURRENT_TIMESTAMP),
(2,'siva@gmail.com','$2a$10$UFEPYW7Rx1qZqdHajzOnB.VBR3rvm7OI7uSix4RadfQiNhkZOi2fi','Siva Prasad', 'ROLE_USER', CURRENT_TIMESTAMP);


insert into posts(id, uid, content, created_by, created_at) values
(1,'ecb3d91f-a0c0-4772-a0be-a70446fb44a6','SDKMAN is an awesome tool for Java devs. #Java', 1, '2014-01-20'),
(2,'4aee4cd5-8e3e-4a90-a0cb-9633267a56c7', 'Mkdocs is a nice tool for creating #documentation site', 2, '2014-01-21'),
(3,'3274f312-3e2c-423d-a12a-628a3e4c73cb','Clean Code...How clean is clean enough!!! #CleanCode',2, '2014-01-25'),
(4,'92617590-ebeb-4b70-afb8-ed88c85ba440','Have you tried Spring Shell? Its nice. #CLI',2, '2014-02-10'),
(5,'ad722ff8-a4de-400d-b124-504dd6c9630e','Quarkus DevServices UI is awesome. #Java #Quarkus',2, '2014-02-14'),
(6,'6092ed6d-280e-4549-9bdb-865049a8354e','#Testcontainers is a must have library in your testing toolbox',2, '2014-03-20'),
(7,'21a7cab4-4ce7-4759-8cf5-12b14cefc964','Use #HTMX and make your life simple. You are welcome!',2, '2014-04-05'),
(8,'2d0925a3-d11c-4177-8c54-ab4891b9a866','As a Java Developer, I like #Angular.',2, '2014-04-16'),
(9,'a299b589-c41c-42cd-8560-5ddaa1ca270f','#Terraform or AWSCDK?',2, '2014-05-10')
;

insert into user_favourites(user_id, post_id, created_at) values
(1, 1, CURRENT_TIMESTAMP),
(1, 2, CURRENT_TIMESTAMP),
(2, 1, CURRENT_TIMESTAMP),
(2, 6, CURRENT_TIMESTAMP),
(2, 8, CURRENT_TIMESTAMP);
