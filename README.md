# oauth-2FA
This is a system that implements OAUTH 2.0. You need a e-mail and an e-mail server. We used SMTP as our e-mail server, but cannot provide external users access. 

The goal is for the user to be authenticated, using the correct credentials along with the 6-digit code from the entered e-mail.

You access a local HTML-file which uses a local Jooby server to authenticate user credentials through an SQLite database.

To be able to use the server;

You need to modify the SQLite database manually, if you want to create a user. You need to add your e-mail and a password. The 'emailcode' coloumn should not be modified. A simple tool for this is 'DB Browser for SQLite'.
In the application.conf in the Jooby server project, you need to go to the application.conf and change it to the local destination of the my-app folder.
If you don't use IntelliJ, you have to run the app from CMD. You do this by navigating inside the folder my-app, then enter the command "mvn jooby:run".
