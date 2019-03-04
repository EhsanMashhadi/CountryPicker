
# Country Picker
[![](https://jitpack.io/v/EhsanMashhadi/CountryPicker.svg)](https://jitpack.io/#EhsanMashhadi/CountryPicker)


Powerful country picker for Android.


<img src="https://github.com/EhsanMashhadi/CountryPicker/blob/master/art/english_bottomsheet.png" width="250">     <img src="https://github.com/EhsanMashhadi/CountryPicker/blob/master/art/persian_dialog.png" width="250">

## Download

1. **Add the JitPack repository to your build file**

 Add it in your project level build.gradle:
```
    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
```

2. **Add the dependency**
Add in in your app module level build.gradle
```
    dependencies {
        implementation 'com.github.EhsanMashhadi:CountryPicker:0.1.0'
    }
```
## How to use

### Simple usage:
```
    CountryPicker countryPicker = new CountryPicker.Builder(this)
    .setCountrySelectionListener(country ->
    Toast.makeText(this, "Selected Country: " + country.getName(), Toast.LENGTH_LONG).show())
```

### Showing flag
```
    countryPicker.showingFlag(true);
```

### Showing dial code
```
    countryPicker.showingDialCode(true);
```

### Showing search
```
    countryPicker.enablingSearch(true);
```

### Sort by
#### Sort method :
- COUNTRY
- DIALCODE
- CODE
- NONE
```
    countryPicker.sortBy(COUNTRY);
    countryPicker.sortBy(DIALCODE);
    countryPicker.sortBy(CODE);
    countryPicker.sortBy(NONE);
```

### View type
#### View type :
- DIALOG
- BOTTOMSHEET
```
    countryPicker.setViewType(DIALOG);
    countryPicker.setViewType(BOTTOMSHEET);
```

### Language
#### Language :
- EN
- FA
```
   countryPicker.setLocale(new Locale("EN"));
   countryPicker.setLocale(new Locale("FA"));
```

### Preselected country
```
    countryPicker.setPreSelectedCountry("iran");
```

### Style
#### Style :
- CountryPickerLightStyle
- CountryPickerDarkStyle
```
    countryPicker.setStyle(R.style.CountryPickerLightStyle);
    countryPicker.setStyle(R.style.CountryPickerDarkStyle);
```

### Except countries
```
    List<String> countries = new ArrayList();
    countries.add("Germany");
    countries.add("Italy");
    countryPicker.exceptCountriesName(countries);
```

### Set countries
```
    List<String> countries = new ArrayList();
    countries.add("Germany");
    countries.add("Italy");
    countryPicker.setCountries(countries);
```

### Auto detect country
#### Detection method :
- Locale
- Sim
- Network

```
    countryPicker.enableAutoDetectCountry(CountryPicker.DetectionMethod.LOCALE
        , country -> Toast.makeText(this, "Detected Country: " + country.getName()
            , Toast.LENGTH_LONG).show())
```