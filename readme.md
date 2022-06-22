# Description for provided endpoints
---
## App is configured to be run on port 8081 on a h2 file database with a create-drop setting.
## All settings can be changed in application.properties as needed.

## Available endpoints
---
1. /courts
    * /all - returns Iterable of all Courts
    * /{id} - for entered id returns an Iterable with Reservations
    * /new - new court with a Post method only price is required in request body
   ```json lines
   {
   "price" : 15
   }
   ```
2. /client
    * /{phone} - for entered phone return Iterable with all Reservations for this client
3. /reservations - Iterable of all Reservations
    * /new - you can send post requests here and if they're valid you will get a price for reservation back,
    * conditions are that the court isn't reserved in this time and the court with said id exists example request body as json 
   ```json lines
   {
   "phone" : "+420 225 312 556",
   "name" : "Novak Djokovic",
   "courtID" : 2,
   "from" : "2022-06-23T16:30:00.000+01:00",
   "to" : "2022-06-23T18:30:00.000+01:00",
   "players" : 4
   }
   ```
   there are different options for the time format, however I found this one to be the most convenient
