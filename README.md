# InnovexSupervisorSpringBoot
Backend application for Innovex Supervisor mobile.

## How to run
1. Open included code in intellij IDEA or other IDE of choice.
2. Generate a firebase service account json and include the entire json in your system environment.
3. Mine will be included in submitted zip for functionality testing withought checking the database.
4. Run the application locally.
5. To test endpoints, use https://innovex-supervisor.herokuapp.com as the base url. And hit the following endpoints:

Note: All endpoints are GET requests
* /tasks/start Starts the application generation of tasks.
* /tasks/stop Stops the task analysis from scheduling new tasks.
* /tasks/clear Clears the database of all recorded tasks
* /tasks/running Gets all tasks that are currently running.
* /tasks/report Gets all tasks including running and completed tasks.
