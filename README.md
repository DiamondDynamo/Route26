# Route26

This is an Android application built by an I4I group at Juniata College for the Huntingdon County Visitor's Bureau. It is intended to be a unified platform for local content producers to post cultural events, and then community members to find events which they would like to attend. Practically, the app serves as the front end for the API, which is hosted on Google Cloud Services. 

## Getting Started

To run the application, an updated APK file must be generated on Android Studio, and then installed on an Android Phone.

Building the APK can be achieved with the following steps:

* First, with the project open in Android Studio, simply click the "Build" drop-down menu at the top
* Next, select "Build APK(s)" from the menu, and wait for the project to build (this my take up to a minute)
* To quickly and easily find the built APK, click "locate" in the bottom right after the building is finished

Once the APK has been generated, it is a relatively simple process to install it on an Android phone

* First, transfer the APK file from your computer to the phone. This is most easily done by connecting the phone with a USB cable, and copying the file into any folder on the phone.
* Once the file is transferred, open the file manager of your choice on your phone, navigate to the file, and tap it
* This will open an installation dialog, which you can complete by following the on-screen prompts
* In most cases, you will need to allow Android to install apps from "unknown sources". This is expected, as the file was not obtained on the Play Store. Most modern phones will take you to this setting, and allow you to only enable it for this single installation

At this point, the app is installed, and you can run it like any other Android application 

### Prerequisites

To run the app, all that is needed is an Android device with at least Android 5.0 (Lollipop)

For development, a computer with a current Windows, Mac, or Linux operating system is required. (If you do not have any of those, lubuntu, a lightweight Linux operating system is available for free [here](https://lubuntu.net/))

Additionally, Android Studio is requried, which can be found [here](https://developer.android.com/studio/) (Simply follow the onscreen instructions to complete installation)

### Installing

A step by step series of examples that tell you have to get a development env running

Once an appropriate operating system and Android Studio have been obtained, the project must be imported into Android Studio.

* When starting up Android Studio, select "Check out project from Version Control", and then choose "Git"
* Then, copy the repository URL available at the top of the page, and paste it into the dialog. Then, set the location and name to whatever you desire, and confirm.
* Due to an issue with the current contents of the repository, after opening the project for the first time, you must click "Details" on the error about the modules in the bottom left, then click on "Deleted Selected"
* Finally, click the "File" drop down menu in the top left, then "Sync Project With Gradle Files"
* Proceed with development as desired


## Built With

* [Android Studio](https://developer.android.com/studio/) - The IDE used for development



## Authors

* **Catherine Bein** - *Initial work*


## Acknowledgments

* Louis Frank for writing the API code
* Peter Ribaudo for being project manager
* GitHub user PuprleBooth for the ReadMe template

