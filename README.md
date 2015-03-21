--------------------------
nRemote v1.8.0a (March 21st, 2015)
--------------------------
Authors : Adriweb, Levak
Thanks to Jim Bauwens for some calc<->computer protocol ([en/de]code algorithms)
http://tiplanet.org


I   - About
II  - How to install
III - How to use
IV  - Known bugs
V   - Changelog
VI  - License


#I - About :
-----------
nRemote is a Java program designed to remote control one or multiple TI-Nspire handhelds when connected to your PC or Mac, whether directly via USB, or via the Navigator Wireless system.  
nRemote also features sequence recording and playing in order to easily execute a set of key presses.  
nRemote can be used for educational purpose in order to synchronise every student's handheld state or by showing a demonstration for a program...

![Overall preview](http://i.imgur.com/IhVB1.jpg)

#II - How to install :
---------------------
1. Install Java JRE 1.8 if your system doesn't have it already.
2. You may have installed any 3.6/3.9 version of TI-Nspire Computer Software (Navigator or not, Teacher or Student does not matter) before using nRemote. This in fact restrains the usage of nRemote to PC and Mac users only. Linux users may find workarounds with WINE.
3. Browse to the fodler where TI-Nspire family computer software is installed (for example in C:\Program Files (x86)\TI Education\TI-Nspire CAS Teacher Software\  ;  use "Show package Contents" on Mac)), and go inside where the Java files are ("Java" folder inside, probably).
4. Copy and paste the file "nRemote.jar" there, with all the other TI .jar files.


#III - How to use :
------------------
1. *Launch your TI-Nspire family computer software FIRST*
2. Open "nRemote.jar"
    
For any platform, you may also try to launch it via terminal ("java -jar [path_to_the_folder]/nRemote.jar")
It can be interesting to create a shortcut of "nRemote.jar" anywhere you want.


#IV - Known Issues :
-----------------
* PC :  
    Q1: nRemote is stuck on "FAILED TO INITIALIZE" and TI-Nspire Computer Software can't see my handhelds !  
    A1: It appears you launched nRemote before launching TI-Nspire Computer Software. This leads to a strange behavior and can be resolved either by going to the Task Manager and killing java.exe/javaw.exe and TI-Nspire Computer Software, or by restarting Windows in extreme cases.  

* Mac :  
    Q2: The GUI may look flat with red dots.  
    A2: There may be a Java version conflict (1.6/1.7). Open a Terminal window, try "java -jar [the nRemote.jar full path]".  

* General :  
    Q3: nRemote says (in its title) that one (or more) device is connected, but there is none.  
    A3: Wait a little bit or use the refresh option in TI-Nspire Computer Software in order to manually remove or add handhelds from the global communication system. For example, the Navigator Wireless System has a window where you can see the connected devices. There, press the Refresh Button.  
    Q4 : Some keys don't work.  
    A4: Well, some keys like exp() don't work and we don't know yet how to solve that. It's not directly a bug related to nRemote.  
    Q5 : Screen doesn't show in v1.8.0a  
    A5: Indeed... not sure why. Will fix later.


#V - Changelog :
----------------
- v0.9 : *Private*. No GUI, Console Only. Basic sendEvents.
- v0.99 : *Private*. Basic GUI. Bugfixes etc.
- v1.0 : *Private*. Improved GUI. Bugfixes etc.
- v1.01 : *Private*. Improved GUI. Bugfixes etc.
- v1.02 : *Private*. Improved GUI. Bugfixes etc.
- v1.1 : *Private*. "--no-screenshots" CLI option added to allow no-delay text typing, - Smaller overall code, Shift-Hold-xxxxx keys now working, Meta-key support (i.e : Mac's Cmd => Nspire's Ctrl), Version displayed in the frame
- v1.2 : *Private*. interface redone from scratch : better resizing. GUI option to disable screen.
- v1.3 : *Public Release*. Reduced delays. Sequences. Bugfixes etc.
- v1.4 : *Public* Error msg fixed. Drag and Drop transfer any files. Calculator target(s) selection. Fixed the missing 1.6 java target flag.
- v1.5 : *Public* Read devices selection done. Application icon added. Overall code cleaned.
- v1.6 : *Public* Screen auto-scaling when the window is being resized.
- v1.7 : *Public* Additional, separate Screen frame ; improvements. "Private" background work on two-way communication (calc<->computer) : Internet access working (tested : calc-calc and IRC chat, web browser, wolfram alpha API call)
- v1.7.1 : *Public* Fixed the always-focused window.
- v1.7.1c : *Public* Cleaned some prints, rebuilt (I hope) for 1.6, finally the changed version number in the window
- v1.8.0a : *Public* Quickly made it compatible with 3.6/3.9 (not compatible with older versions anymore). Not tested on Windows. Real-time screen seems broken, not sure why.

Future :
- Internal Sequence Editor  
- Internet control (to allow some kind of remote internet assistance)  -  Could be done soon, maybe idk  
- Getting keypresses from the handheld to the computer ?  -  Done in a tricky way.


#VI - License :
-------------
WTFPL License ( http://sam.zoy.org/wtfpl/ ). But also thank us. And visit http://tiplanet.org :)
