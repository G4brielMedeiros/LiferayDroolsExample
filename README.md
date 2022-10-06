# Drools Plugin demonstration as a REST controller

> This project does not follow standard practices such as separation between service/controller layers, exception handling, rich response returns, etc.
> This is intentional so that the code is free of distractions and focuses on the RulesEngine itself.

To test the API, send the following payload to `localhost:8080/o/getRequiredDocuments`:

```json
{
	"country": "USA",
	"position": "developer",
	"canDrive": true
}
```

change the rules file or the payload to test the implementation.