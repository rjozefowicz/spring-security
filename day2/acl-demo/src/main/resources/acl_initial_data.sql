INSERT INTO acl_sid (id, principal, sid) VALUES
  (99, 1, 'xyz@xyz.com');

-- TODO #1 dodaj do tabeli acl_sid użytkowników stworzonych w AclDemoApplication. w kolumnie principal ustaw 1 (0 oznacza rolę)

INSERT INTO acl_class (id, class) VALUES
  (99, 'xyz.Xyz');

-- TODO #2 dodaj do tabeli acl_class klasę Project (uwzględniając pakiet)

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 1, 0), --project1
  (2, 1, 2, NULL, 1, 0), --project2
  (3, 1, 3, NULL, 1, 0); --project3

-- TODO #3 dodaj instancję 3 obiektów klasy Project do tabeli acl_object_identity (parent_object = NULL, owner_sid = 1, entries_inheriting = 0)

-- acl_object_identity refers to acl_object_identity table, sid refers to acl_sid. mask = 1 (READ), 2 (WRITE), ... 16 (ADMIN)
INSERT INTO acl_entry
  (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 1, 1, 1, 1, 1); --jan READ project1

-- TODO #4 wzorując się na wpisie jan READ project1 dodaj:

--jan WRITE project1
--stefan READ project1
--jan READ project2
--kalina READ project2
--kalina WRITE project2
--marcelina READ project3
--joanna READ project3
--katarzyna READ project3
--katarzyna WRITE project3
--jan READ project2

-- TODO #5 przetestuj np postmanem różnych użytkowników uderzając na endpoint /projects + /projects/{id} + PUT /projects/{id}