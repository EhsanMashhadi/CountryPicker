
# Country Picker
[![](https://jitpack.io/v/EhsanMashhadi/CountryPicker.svg)](https://jitpack.io/#EhsanMashhadi/CountryPicker)


Powerful country picker for Android.


![CountryPicker](https://github.com/EhsanMashhadi/CountryPicker/blob/master/art/countrypicker.gif?raw=true)
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

### Simple usage
```
     CountryPicker countryPicker = new CountryPicker.Builder(this).setCountrySelectionListener(new RecyclerViewAdapter.OnCountryClickListener() {

                @Override
                public void onCountrySelected(Country country) {

                    Toast.makeText(MainActivity.this, "Selected Country: " + country.getName(), Toast.LENGTH_LONG).show();
                }
            }).build();

      countryPicker.show(this);
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
- DIALOG
- BOTTOMSHEET
```
    countryPicker.setViewType(DIALOG);
    countryPicker.setViewType(BOTTOMSHEET);
```

### Language
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
- CountryPickerLightStyle
- CountryPickerDarkStyle
```
    countryPicker.setStyle(R.style.CountryPickerLightStyle);
    countryPicker.setStyle(R.style.CountryPickerDarkStyle);
```

For using custom theme you can declare style in style.xml
```
    <style name="CountryPickerCustomStyle">
        <item name="countryNameColor">@color/colorBlue</item>
        <item name="dialCodeColor">@color/colorBlue</item>
        <item name="rowBackgroundColor">@color/colorWhite</item>
        <item name="rowBackgroundSelectedColor">@color/colorGrey</item>
    </style>
```
and then:
```
    countryPicker.setStyle(R.style.CountryPickerCustomStyle);
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
- Locale
- Sim
- Network

```
    countryPicker.enableAutoDetectCountry(CountryPicker.DetectionMethod.LOCALE
        , country -> Toast.makeText(this, "Detected Country: " + country.getName()
            , Toast.LENGTH_LONG).show())
```
