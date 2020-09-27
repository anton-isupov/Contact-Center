delete application;
delete contact;

insert into contact(CONTACT_ID) values ('uuid');

insert into application (APPLICATION_ID, DT_CREATED, PRODUCT_NAME, CONTACT_ID)
values ('1', TO_DATE('17/12/2015', 'DD/MM/YYYY'), 'app1', 'uuid');

insert into application (APPLICATION_ID, DT_CREATED, PRODUCT_NAME, CONTACT_ID)
values ('2', TO_DATE('17/12/2016', 'DD/MM/YYYY'), 'app2', 'uuid');

insert into application (APPLICATION_ID, DT_CREATED, PRODUCT_NAME, CONTACT_ID)
values ('3', TO_DATE('17/12/2018', 'DD/MM/YYYY'), 'app3', 'uuid');
