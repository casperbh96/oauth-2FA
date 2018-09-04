# oauth-2FA
This is a system that implements OAUTH 2.0. You need a e-mail and an e-mail server. We used SMTP as our e-mail server, but cannot provide external users access. 

The goal is for the user to be authenticated, using the correct credentials along with the 6-digit code from the entered e-mail.

You access a local HTML-file which uses a local Jooby server to authenticate user credentials through an SQLite database.

***To be able to use this project:

In EmailAuth.SendMail, you need to edit the username and password to an e-mail you provide. That e-mail needs to be hooked up to some SMTP service that sends e-mails by request. Remember to edit the properties if need be. Our properties will most likely not work for you.

Next, we have a single SQLite file, which you need to manually add users to. It was not a part of the project to be able to create users, but the project is scaleable and can be further developed to match further needs.
In the application.conf file, you need to edit the replace the <> tags (yes, also the <>) as specified.

When you enter some correct credentials, i.e. an existing username and password in the same row in the database, the service will send an e-mail to the user. Then that user needs to go to that e-mail to grab to 6-digit pseudo random generated number. For testing purposes, we found it to be easier to reload the database and copy-paste from the emailcode field.
