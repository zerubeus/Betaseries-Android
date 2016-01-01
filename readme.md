# TV SHOWS (Rest App)

TODO: 

- Get and display data from a rest interface : OK
- Usage of the standard Android API (recycleview, intent, toast, thread ...) : OK
- Layouts needs to use resources to display and translate strings (in EN and FR) : OK
- API Betaseries ( MY KEY : "cf4258cf28b7" a reminder for me :p ) : OK

# NOTES

- The Betaseries does't have a single one JSON with all Data needed so this was the hardest part for us, the solution was to use callbacks to manage multiple JSON file because JsonObjectRequest is Async so we can't use the function to return a value.
- For the the HTTP requests we have only used the GET method with the powerful Android Volley <3
- YES so the trickiest part so far was the callbacks I'm not sure it's the best way to manage Async tasks in JAVA but YEAH .. ( thanks to my NodeJs background :3 ) 
- Finely please apologies my spaghetti code style :p


## Installation

- Git clone this repo 
- import it in Android studio and let Gradle do the job for you :)

```javascript
    compileSdkVersion 23
    buildToolsVersion '23.0.0'
```

## Usage

I'll add the Play store link here as soon as possible ;)

## Contributing

1. Fork it!
2. Create your feature branch: `git checkout -b my-new-feature`
3. Commit your changes: `git commit -am 'Add some feature'`
4. Push to the branch: `git push origin my-new-feature`
5. Submit a pull request :D

## Team Members 

- Jebali Ala Eddine
- Amri Kouki Sarra

## Credits

Special thanks to Pierre Emmanuel Fabrigli our lovely professor

## License

MIT