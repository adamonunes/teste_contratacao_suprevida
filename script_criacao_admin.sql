-- usuário : suprevida_admin - senha: Suprevid@
INSERT INTO public.users(
	name, password)
	VALUES ('suprevida_admin', '$2a$10$pxkVx/lkZG1UQZaoghtpdu54LSVTz1DQYgcNPl0GNoXA33YQHP3yq');

INSERT INTO public.roles(
	name)
	VALUES ('ROLE_ADMIN');


INSERT INTO public.users_roles(
	user_id, role_id)
	VALUES (1,1);