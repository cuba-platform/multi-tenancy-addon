-- begin CUBASDBMT_TENANT
alter table CUBASDBMT_TENANT add constraint FK_CUBASDBMT_TEN_ON_ACCGRO foreign key (ACCESS_GROUP_ID) references SEC_GROUP(ID)^
alter table CUBASDBMT_TENANT add constraint FK_CUBASDBMT_TENANT_ON_ADMIN foreign key (ADMIN_ID) references SEC_USER(ID)^
create unique index IDX_CUBASDBMT_TE_UK_ACGRID on CUBASDBMT_TENANT (ACCESS_GROUP_ID, DELETE_TS) ^
create unique index IDX_CUBASDBMT_TEN_UK_ADMIN_ID on CUBASDBMT_TENANT (ADMIN_ID, DELETE_TS) ^
create unique index IDX_CUBASDBMT_TENANT_UK_NAME on CUBASDBMT_TENANT (NAME, DELETE_TS) ^
create index IDX_CUBASDBMT_TEN_ON_ACCGRO on CUBASDBMT_TENANT (ACCESS_GROUP_ID)^
create index IDX_CUBASDBMT_TENANT_ON_ADMIN on CUBASDBMT_TENANT (ADMIN_ID)^
-- end CUBASDBMT_TENANT
