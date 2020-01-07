up:
	docker-compose up --build --force-recreate --remove-orphans --detach

down:
	docker-compose down
	yes | docker container prune
	yes | docker volume prune

follow-logs:
	docker-compose logs --follow

clean:
	find . -type f -name ".DS_Store" -delete
