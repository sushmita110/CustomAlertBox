## CustomAlertBox

Custom alert box is created in order to maintain uniformity in design for both android and ios platform as both platform have their default
alert box and alert view.

## Version
Minimum sdk version: 23
Target sdk version: 30
Version Name: 0.0.1

## Technologies
IDE : Android Studio [4.1.2]

## Language
Kotlin

## Installation process
[Clone with HTTP**  for Android]
- https://github.com/sushmita110/CustomAlertbox.git

[Clone with SSH** for Android]
- git@github.com:sushmita110/CustomAlertbox.git

[Foe ios, similar module has been created, read README file to know further installation process]
- https://github.com/PrashaOutcode/CustomAlertView.git
- git@github.com:PrashaOutcode/CustomAlertView.git

## Features
- Custom alert box has used the view binding so don`t forget to add 
[
buildFeatures {
viewBinding true
} 
] in your build.gradle.
- Custom alert box offers similar feature like the default one where users can add a alert title, alert message and buttons with their
  corresponding action.
- User can add as many buttons as they want, which will be shown in the fashion below.
- Model is create to customize the label and buttons with the required style.
- User can change the color of label and button according to the project need.
- Added animation in alert box.

## Steps to follow
- In order to implement alert box in your app, copy or drag "CustomAlertBox" folder in you project directory.  
- Button is used as an Recyclerview so you can add as many button as you like and add the action "btn_action" to hold the naviagtion of button. 
- Then, initialise buttonTitle globally as

[kotlin]
[ val actionText = mutableListOf<AlertViewDataModel>() ]

- You can create a function in  which it will call from your btn_action action and holds all the functionality for alert box 
- Now, to configure alert buttons we need to initialize button as per our need

 [
  val actionLogout = AlertViewDataModel(
                "Yes, logout",
                onItemClick = {
                //add logout button action here
                }
            )
            val actionCancel = AlertViewDataModel(
                "Cancel",
                onItemClick = {
                //add cancel button action here
                }
            )
            ]
            
- We now add these two button in our variable actionOk by adding them as 
[
actionText.add(actionLogout)
actionText.add(actionCancel)
]
- Finally, now we create alert box by adding the datas in button, now we initialize a variable as
  [
  val alertData = AlertViewModel(
                 title = "Logout",
                 message = "Are you sure you want to logout?",
                 actionText
             )  
 ]
 in while we add the title, message and the list of buttons.
 
- Now our code base looks like this:
[
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var customAlertViewActivity: CustomAlertViewActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnClick.setOnClickListener {
                    showAlertBox()
                }
    }
    private fun showAlertBox(
         val actionText = mutableListOf<AlertViewDataModel>()
    
              val actionLogout = AlertViewDataModel(
                              "Yes, logout",
                              onItemClick = {
                              }
                          )
                          val actionCancel = AlertViewDataModel(
                              "Cancel",
                              onItemClick = {
                              }
                          )
    
                actionText.add(actionLogout)
                actionText.add(actionCancel)
    
                val alertData = AlertViewModel(
                    title = "Logout",
                    message = "Are you sure you want to logout?",
                    actionText
                )
    
                customAlertViewActivity = CustomAlertViewActivity(
                    onActionItemClick = {
                    }
                )
                customAlertViewActivity.showAlertView(this, alertData)
            })
}
]

- Optional, you can create your own style for alert buttons by adding the style. A new model is created to add style in the buttons with the data class name 
[
data class ConfigModel(
var title: String? = null,
var showTitle: Boolean? = false,
var titleStyle: Int? = null,
var itemStyle: Int? = null,
var backgroundDrawable: Int? = null,
var tintColor:Int? = null)
]
You can create dataSource in showAlertView().

[       
 customAlertViewActivity.showAlertView(this, alertData, ConfigModel(tintColor = R.color.colorPrimary))
]

You just created a customizable alert box.
   


