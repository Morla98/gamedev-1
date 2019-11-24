up:
	docker-compose up --build --force-recreate --remove-orphans --detach db pgadmin

down:
	docker-compose down
	yes | docker container prune
	yes | docker volume prune

follow-logs:
	docker-compose logs --follow
