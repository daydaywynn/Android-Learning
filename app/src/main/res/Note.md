# Notes about ViewModels
- Use ViewModels to make save UI Data/states that survive device/screen rotation.
- use ViewModels for data formatting for UI also
- Use SavedStateHandle to avoid the UI state loss on process death issue. 
  - The SavedStateHandle allows you to be able to store data outside the apps process

- using the 'by' keyword
  - 'by' is a property delegate. A property delegate is how you delegate functionality of a property to an external unit of code. 
    - another property delegate is 'lazy'
      - 'lazy' allows for saving resources by waiting to initialize the property ONLY when it's accessed. 
- the viewModels() creates and returns an instance of the ViewModel that stays around until the activity is finished. 