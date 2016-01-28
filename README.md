# clojure-mars-rover-data

Quick Clojure project to retrieve data from Nasa's Mars Rover Photos API

## Usage
```
(:body (get-rover-data (generate-url {:sol 50})))
(:body (get-rover-data (generate-url {:sol 50 :camera "FHAZ"})))
```
Pass a hashmap of whichever URL parameters you want to pass to the API. Parameters can be found here: https://api.nasa.gov/api.html#MarsPhotos
