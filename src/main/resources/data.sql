INSERT INTO "public"."client" ("address", "age", "code", "full_name", "gender", "phone", "client_id", "password", "status") 
VALUES ('Guayaquil', 33, '0923277883', 'Bryan Valencia', 'M', '123456789', 'bryan91', '123', 't');
INSERT INTO "public"."client" ("address", "age", "code", "full_name", "gender", "phone", "client_id", "password", "status") 
VALUES ('Guayaquil', 23, '0123456789', 'Alex Roca', 'M', '123456789', 'alex25', '123', 't');
INSERT INTO "public"."client" ("address", "age", "code", "full_name", "gender", "phone", "client_id", "password", "status") 
VALUES ('Guayaquil', 19, '0963258741', 'Juan Castro', 'M', '123456789', 'juan70', '123', 't');
INSERT INTO "public"."client" ("address", "age", "code", "full_name", "gender", "phone", "client_id", "password", "status") 
VALUES ('Guayaquil', 24, '0147852369', 'Pedro Barzola', 'M', '123456789', 'pedro30', '123', 't');


INSERT INTO "public"."account" ("account_number", "account_type", "balance", "status", "client_fk") 
VALUES (452185, 'AHO', '759.05', 't', 1);
INSERT INTO "public"."account" ("account_number", "account_type", "balance", "status", "client_fk") 
VALUES (452186, 'CTE', '0.00', 't', 1);
INSERT INTO "public"."account" ("account_number", "account_type", "balance", "status", "client_fk") 
VALUES (526398, 'AHO', '0.00', 't', 2);

INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:15:59.84117', 'EGR', '40.00', 1, '200.00', '160.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:08.940053', 'ING', '400.00', 1, '160.00', '560.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:16.54931', 'EGR', '100.00', 1, '560.00', '460.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance")
VALUES ('2024-08-01 11:16:23.2329', 'ING', '350.00', 1, '460.00', '810.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:26.651704', 'ING', '35.00', 1, '810.00', '845.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:29.906494', 'ING', '78.00', 1, '845.00', '923.00');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:43.325071', 'EGR', '63.95', 1, '923.00', '859.05');
INSERT INTO "public"."transaction" ("date_time", "transaction_type", "value", "account_fk", "old_balance", "new_balance") 
VALUES ('2024-08-01 11:16:48.372802', 'EGR', '100.00', 1, '859.05', '759.05');

