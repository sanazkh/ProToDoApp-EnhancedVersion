# Pro-To-Do---DB
# Pre-work - *Pro To-Do DB Application*

**Pro To-Do Application* is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Sanaz Khosravi**

Time spent: **2** hours spent in total

## A user can add a task, delete, or edit a task.

The following **required** functionality is completed:

* User can **successfully add and remove items** from the todo list
* User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* Add support for selecting the priority of each todo item (and display in listview item)
* Tweak the style improving the UI / UX, play with colors, images or backgrounds

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='http://i.imgur.com/kLbPhtu.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** Android development is fun and filled with learnings. As a developer, I was able to explore different features of Android and found it more appealing compared to iOS platform. Using Java as the main development language is also very powerful. Java is a powerful language that is perfectly utilized in Android development. The MVC model which is used in Android development organizes the code perfectly and allows developers to collaborate without interfering each other's work. 

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** Adapter is an intermediate between date and view in Android. AdatreViews are responsible for displaying of the child views that are passed by an adapter. In this application, ListView that we are using to show the list of tasks is a AdapterView. ArrayAdapter connects array to the AdapterView. convertView is used to use an old view. If we want to use the old view, we need to first check to see it is null or not or there is an issue with type compatibility. If either of the cases is true, a new view should be created otherwise the old view can be recycled and used. 

## Notes

Learning how to use arrayadapters and designing a customadapter was a bit challanging at the beginning.  

## License

    Copyright [2017] [Sanaz Khosravi]

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
