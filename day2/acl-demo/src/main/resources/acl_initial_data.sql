INSERT INTO acl_sid (id, principal, sid) VALUES
  (1, 1, 'jan@example.com'),
  (2, 1, 'stefan@example.com'),
  (3, 1, 'kalina@example.com'),
  (4, 1, 'marcelina@example.com'),
  (5, 1, 'joanna@example.com'),
  (6, 1, 'katarzyna@example.com');

INSERT INTO acl_class (id, class) VALUES
  (1, 'dev.jozefowicz.springsecurity.acldemo.model.Project');

INSERT INTO acl_object_identity
  (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
  VALUES
  (1, 1, 1, NULL, 1, 0), --project1
  (2, 1, 2, NULL, 1, 0), --project2
  (3, 1, 3, NULL, 1, 0); --project3

-- acl_object_identity refers to acl_object_identity table, sid refers to acl_sid. mask = 1 (READ), 2 (WRITE), ... 16 (ADMIN)
INSERT INTO acl_entry
  (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
  VALUES
  (1, 1, 1, 1, 1, 1, 1, 1), --jan READ project1
  (2, 1, 1, 1, 2, 1, 1, 1), --jan WRITE project1
  (3, 1, 1, 2, 1, 1, 1, 1), --stefan READ project1
  (4, 2, 1, 1, 1, 1, 1, 1), --jan READ project2
  (5, 2, 1, 3, 1, 1, 1, 1), --kalina READ project2
  (6, 2, 1, 3, 2, 1, 1, 1), --kalina WRITE project2
  (7, 3, 1, 4, 1, 1, 1, 1), --marcelina READ project3
  (8, 3, 1, 5, 1, 1, 1, 1), --joanna READ project3
  (9, 3, 1, 6, 1, 1, 1, 1), --katarzyna READ project3
  (10, 3, 1, 6, 2, 1, 1, 1), --katarzyna WRITE project3
  (11, 3, 1, 1, 1, 1, 1, 1), --jan READ project3
  (12, 2, 1, 6, 1, 1, 1, 1); --katarzyna READ project2