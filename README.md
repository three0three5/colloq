# colloq

### API

Реализованы следующие эндпоинты:

`GET api/mus/songs` - для получения информации о всех треках

`GET api/mus/songs/{id}` - для получения информации о треке с конкретным id

`POST api/mus/ratings` - позволяет менять оценку трека

`POST api/mus/playlists/` - позволяет создать новый плейлист

`POST api/mus/playlists/{id}/songs` - позволяет добавить в плейлист с определенным id существующий трек

### База данных

База данных состоит из двух таблиц - Song и Playlist.

Сущность Song состоит из столбцов id, author, duration, genre, name, rating, playlist_id.

Сущность Playlist состоит из столбцов id, name.

Связь между Playlist и Song - OneToMany, без ленивой инициализации.

Заранее были добавлены следующие песни:
```
INSERT INTO public.song (author, duration, genre, name, rating, playlist_id) 
VALUES ('Jane Smith', 220, 'Rock', 'Thunder', 5, NULL), 
       ('Bob Johnson', 150, 'Jazz', 'Moonlight Sonata', 3, NULL), 
       ('Tom Davis', 300, 'Country', 'Dirt Road Anthem', 4, NULL);
```
