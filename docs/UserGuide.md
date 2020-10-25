---
layout: page
title: My Fitness Buddy User Guide
---

* **Table of Contents**
    * Introduction
    * About
    * Quick Start
    * Features
    * FAQ
    * Command Summary 

--------------------------------------------------------------------------------------------------------------------
## Introduction

### Product overview

My Fitness Buddy is a desktop application that helps Full-Time National Servicemen with PES ‘BP’ Status track 
their overall change in fitness level by managing their daily calorie input/output and weight changes during Basic Military 
Training. 

## About

### Purpose

This document specifies the quick start guide, features and the command summary of
the application, My Fitness Buddy.

### Intended Audience

This User Guide is for any Full-Time National Servicemen that uses the MyFitnessBuddy application. 

## Quick start

1. Ensure you have Java11 or above installed in your Computer.  
2. Download the latest *MyFitnessBuddy.jar*.  
3. Copy the file to the folder you want to use as the home folder for My Fitness Buddy.   
4. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds.    					
5. If the app doesn’t start right away, try opening a command terminal in the folder and enter   
`java -jar MyFitnessBuddy.jar`
1. Refer to the [Features](#features) below for details of each command.  

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.
  e.g. in `add f/FOOD`, `FOOD` is a parameter which can be used as add `f/Laksa`.

</div>

### Creating a new profile : `create`
Create a new profile for a Person.

Format: `create n/NAME id/ID_NUM h/HEIGHT w/TARGET_WEIGHT`

Examples:  
`create n/Johnny id/1234 h/170 w/68` 

### Adding a daily weight record : `add d/DATE w/WEIGHT`
Add a new daily weight record.

Format: `add d/DATE w/WEIGHT`

Examples:  
`add d/2020-10-16 w/70` 

### Editing a daily weight record : `edit INDEX w/WEIGHT`
Edit a new daily weight record.

Format: `edit INDEX w/WEIGHT`

Examples:  
`edit 1 w/65` 

### Adding a calorie input : `calorie tp/in`
Add calorie input to the calorie tracker.

Format: `calorie tp/in t/TIME f/FOOD c/CALORIE COUNT`

Examples:  
`calorie tp/in t/1200 f/laksa c/290`  
`calorie tp/in t/1800 f/drumstick c/76`

### Adding a calorie output : `calorie tp/out`
Add calorie output to the calorie tracker.

Format: `calorie tp/out t/TIME e/EXERCISE c/CALORIE_BURNT`

Examples:  
`calorie tp/out t/1200 e/running c/500`  
`calorie tp/out t/1800 e/swimming c/250`

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### View all calorie inputs and outputs of a day: `Double click on a day`
Shows a list of all entries of calorie inputs and calorie outputs of a particular day.

Image: `image to be added`

### View Statistics: `Command to be added`
Shows all the weight and calorie statistics in a line chart.

![weight_stats_chart](images/weight_stats_chart.png)

Format: `cmd to be added`

### Deleting an input : `delete /in`
Deletes the calorie input at the specified index.  
The index refers to the index number shown in the calorie input list.  

Format: `delete /in i/INDEX`

Examples:  
`delete /in 2`

### Deleting an output : `delete /out`
Deletes the calorie output at the specified index.
The index refers to the index number shown in the displayed calorie output list.

Format: `delete /out i/INDEX`

Examples:  
`delete /out 2`

### Clearing all entries : `clear`
Clears all entries in MyFitnessBuddy.

Format: `clear`


### Exiting the program : `exit`
Exits the program.

Format: `exit`


### Saving the data

My Fitness Buddy data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous My Fitness Buddy home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format
--------|------------------
**Create profile** | `create n/NAME id/ID_NUM h/HEIGHT w/WEIGHT`
**Add a new weight record** | `add d/DATE w/WEIGHT`
**Edit a weight record** | `edit INDEX w/WEIGHT`
**Add input** | `calorie tp/in t/TIME f/FOOD c/CALORIE_COUNT`
**Add output** | `calorie tp/out t/TIME d/DURATION (MINUTES) c/CALORIE_BURNT`
**Delete input** | `delete /in i/INDEX`
**Delete output** | `delete /out i/INDEX`
**View entries** | `view`
**Clear entries** | `clear`
**Help** | `help`
**Exit** | `exit`

