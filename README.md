
# Country Picker
[![](https://jitpack.io/v/EhsanMashhadi/CountryPicker.svg)](https://jitpack.io/#EhsanMashhadi/CountryPicker)


Powerful country picker for Android.


![CountryPicker](https://github.com/EhsanMashhadi/CountryPicker/blob/master/art/countrypicker.gif?raw=true)
## Download

1. **Add the JitPack repository to your build file**

 Add it in your project level build.gradle:
```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. **Add the dependency**
Add in in your app module level build.gradle
```gradle
dependencies {
    implementation 'com.github.EhsanMashhadi:CountryPicker:0.4.0'
}
```
## How to use

### Simple usage
```JAVA
CountryPicker countryPicker = new CountryPicker.Builder(this).setCountrySelectionListener(new RecyclerViewAdapter.OnCountryClickListener() {

    @Override
    public void onCountrySelected(Country country) {

        Toast.makeText(MainActivity.this, "Selected Country: " + country.getName(), Toast.LENGTH_LONG).show();
    }
}).build();

countryPicker.show(this);
```

### Showing flag
```JAVA
countryPicker.showingFlag(true);
```

### Showing dial code
```JAVA
countryPicker.showingDialCode(true);
```

### Showing search
```JAVA
countryPicker.enablingSearch(true);
```

### Sort by
- COUNTRY
- DIALCODE
- CODE
- NONE
```JAVA
countryPicker.sortBy(CountryPicker.Sort.COUNTRY);
countryPicker.sortBy(CountryPicker.Sort.DIALCODE);
countryPicker.sortBy(CountryPicker.Sort.CODE);
countryPicker.sortBy(CountryPicker.Sort.NONE);
```

### View type
- DIALOG
- BOTTOMSHEET
```JAVA
countryPicker.setViewType(CountryPicker.ViewType.DIALOG);
countryPicker.setViewType(CountryPicker.ViewType.BOTTOMSHEET);
```

### Language
- EN
- FA
```JAVA
countryPicker.setLocale(new Locale("EN"));
countryPicker.setLocale(new Locale("FA"));
```

### Preselected country
```JAVA
countryPicker.setPreSelectedCountry("iran");
```

### Style
- CountryPickerLightStyle
- CountryPickerDarkStyle
```JAVA
countryPicker.setStyle(R.style.CountryPickerLightStyle);
countryPicker.setStyle(R.style.CountryPickerDarkStyle);
```

For using custom theme you can declare style in style.xml
```JAVA
<style name="CountryPickerCustomStyle">
<item name="countryNameColor">@color/colorBlue</item>
<item name="dialCodeColor">@color/colorBlue</item>
<item name="rowBackgroundColor">@color/colorWhite</item>
<item name="rowBackgroundSelectedColor">@color/colorGrey</item>
</style>
```
and then:
```JAVA
countryPicker.setStyle(R.style.CountryPickerCustomStyle);
```
### Except countries
```JAVA
List<String> countries = new ArrayList();
countries.add("Germany");
countries.add("Italy");
countryPicker.exceptCountriesName(countries);
```

### Set countries
```JAVA
List<String> countries = new ArrayList();
countries.add("Germany");
countries.add("Italy");
countryPicker.exceptCountriesName(countries);
```

### Auto detect country
- Locale
- Sim
- Network

```JAVA
countryPicker.enableAutoDetectCountry(CountryPicker.DetectionMethod.LOCALE
, country -> Toast.makeText(this, "Detected Country: " + country.getName()
, Toast.LENGTH_LONG).show())
```
