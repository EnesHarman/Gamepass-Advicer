### Gamepass-Advicer
A Rest API that includes 420 current Gamepass games. Users can do CRUD operations. Also, API can suggest a game to the user with some criteria.
 
#### About Data
Database has 420 different games those can play on console. Data has ben collected on 14.11.2021 .

##### End Points 

- List Games : /api/games/list -> It can take 2 paramatres for pagination. These paramatres are pageNo & pageSize
- Get Single Game : /api/games/{gameId}
- Get Random Game : /api/games/random -> Suggest a game to the user with some criteria. It can take 3 paramatre. These parametres are genreName & minUserScore & minMetaScore
- Game Add : /api/games/add -> It takes a game data from body
- Game Update : /api/games/update -> It takes a game data from body
- Game Delete : /api/games/delete/{gameId} 
