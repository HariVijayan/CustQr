This repository contains the source code for a customized qr code generating website.

** To run the website, download the sourcecode zip and run it as a springboot application in any IDE. Preffered IDE: Eclipse IDE for Enterprise Java and Web Developers Version: 2023-12 (4.30.0) **

Qr code is generated using open source ZXing library by google.

Technical Specifications:

Frontend: HTML5, CSS, JavaScript (No Frameworks as of now, will be upgraded soon)
Routing: Ajax
Backend: SpringBoot (Maven)
Template Engine: Thymeleaf
Java Version: Java 21

Current Usage: 

--Regular Qr Code (Show Text upon scanning)
--Function based Qr code: Navigate to URL, Connect to Wi-Fi, Send an Email, Send a SMS, Navigate to Playstore App, Navigate to Appstore App, Open Location in Maps, Create new Personal Contact, Create new Business Contact, Dial Phone Number, Make Calendar Event

Customizations Available:

--Positional Markers Shape (All 3)
--Qr Pattern Shape 
--Overall Size
--Quiet Zone Size
--Addition of a custom logo
--Coloring (Separate coloring for outer positional markers (all 3) and inner positional markers (all 3) along with separate coloring for qr body pattern)
--Custom logo positioning
--Custom logo orientation

**Dark mode is available for the website as well**



