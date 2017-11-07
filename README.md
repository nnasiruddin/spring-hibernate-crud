## Sample Object

```

{
        "name": "John Hancock",
        "company":"ABC",
        "profileImage": "base64:abcdefgh",
        "phonePersonal": "1234567890",
        "phoneWork": "1234567890",
        "birthDate": "1987-05-28",
        "email": "john@gmail.com",
        "address": "124 Marine Drive",
        "city": "Chicago",
        "state": "IL",
        "zip": "60639"
}
```

## Data Type Assumptions:
```
profileImage = String --> Base 64 encoded string
birthDate = Date ---> My SQL Date yyyy-mm-dd
```

## All fields are set as required:
-Phone number has to be unique --> Set that in the DB itself
-Email has to be unique ---> Set that in the DB itself



## This is a RESTful API with the following endpoints:
```
1 - 
Endpoint = {/contact/listByCity/{city}}
RequestMethod = GET

2 - 
Endpoint = {/contact/listByState/{state}}
RequestMethod = GET

3 -
Endpoint = {/contact/searchByEmail}
RequestMethod = POST
Accepted Data Type = application/x-www-form-urlencoded

4 -
Endpoint = {/contact/searchByPhone/{phone}}
RequestMethod = GET

5 -
Endpoint = {/contact/saveContact}
RequestMethod = POST
Accepted DataType = application/json

6 -
Endpoint = {/contact/updateContact}
RequestMethod = PUT
Accepted DataType = application/json

7 -
Endpoint = {/contact/deleteContact/{id}}
RequestMethod = DELETE
```

This REST Service assumes that there is a MySQL Server running with a table with the name 'contact' created. This assumes that the server is running on port 3306.

To change the database settings please update the POM.xml with the correct database settings.
