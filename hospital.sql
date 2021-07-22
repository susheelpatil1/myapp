

create table hospital.patient_info (
Patient_id integer   GENERATED ALWAYS AS IDENTITY (START WITH 1000, INCREMENT BY 1),
first_name varchar(50),
last_name varchar(50),
phone_no VARCHAR(15),
address_ln1 varchar(100),
address_ln2 varchar(100),
city varchar(100),
state varchar(50),
created_dt timestamp not null,
updated_dt timestamp not null,
updated_user varchar(50) not null,
queue_name varchar(50),
next_appt_date timestamp,
CONSTRAINT patient_pk PRIMARY KEY (Patient_id)
);



create table hospital.diagnosis_info (
Patient_id integer not null references hospital.patient_info(Patient_id),  
diagnosis_id integer not null,
diagnisis_txt clob(64 K),
updated_dt timestamp not null,
updated_user varchar(50),
diagnosis_status varchar(50),
CONSTRAINT diagnosis_pk PRIMARY KEY (Patient_id,diagnosis_id)

);


create table hospital.prescription_info (
Patient_id integer not null ,  
diagnosis_id integer not null ,  
prescription_id int not null,
prescription clob(64 K),
updated_dt timestamp not null,
updated_user varchar(50),
prescription_status varchar(50),
CONSTRAINT presc_FK
	FOREIGN KEY (Patient_id, diagnosis_id)
	REFERENCES hospital.diagnosis_info (Patient_id, diagnosis_id),
CONSTRAINT prescritpion_pk PRIMARY KEY (Patient_id,diagnosis_id,prescription_id)
);


create table hospital.user_info(
user_id varchar(50),
password varchar(50),
first_name varchar(50),
last_name varchar(50),
role varchar(50),
updated_dt timestamp not null
);

update hospital.patient_info set first_name='susheel-update',updated_dt=sysdate where patient_id=1000

alter table hospital.patient_info add(next_appt_date timestamp)

CREATE SEQUENCE hospital.diagnosis_id_seq AS BIGINT START WITH 100

CREATE SEQUENCE hospital.PRESCRIPTION_id_seq AS BIGINT START WITH 100



SELECT d.patient_id,d.diagnosis_id,p.prescription_id
FROM hospital.diagnosis_info d 
left JOIN hospital.prescription_info p
ON d.patient_id = p.patient_id and d.diagnosis_id=p.diagnosis_id and p.patient_id=1001
order by d.diagnosis_id desc;
