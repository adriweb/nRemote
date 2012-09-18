--------------------------
nRemote v1.7b (17/09/2012)
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


I - About : 
-----------
nRemote is a Java program designed to remote control one or multiple TI-Nspire handhelds when connected to your PC or Mac, whether directly via USB, or via the Navigator Wireless system.
nRemote also features sequence recording and playing in order to easily execute a set of key presses.
nRemote can be used for educational purpose in order to synchronise every student's handheld state or by showing a demonstration for a program...


II - How to install :
---------------------
1) Install Java JRE 1.6 (or later) if your system doesn't have it already.
2) You may have installed any 3.2 version of TI-Nspire Computer Software, Navigator or not, Teacher or Student does not matter. (Computer Link 3.2 might work but is less recommanded) before using nRemote. This in fact restrains the usage of nRemote to PC and Mac users only. Linux users may find workarounds with WINE.
3) * PC : 
Copy and paste the file "nRemote.jar" to the folder that contains your TI-Nspire family computer software (for example in C:\Program Files (x86)\TI Education\TI-Nspire CAS Teacher Software\).

   * Mac :
Copy and paste the file "nRemote.jar" to the Resources folder inside of your TI-Nspire family computer software. (Right-click on Nspire app, click "Show folder Contents" and open the Contents\Resources\ folder).


III - How to use :
------------------
 * PC :
    1) *Launch your TI-Nspire family computer software 3.2 FIRST*
    2) Open "nRemote.jar"
	
 * Mac : Just open "nRemote.jar"

For any platform, you may also try to launch it via terminal ("java -jar [path_to_the_fodler]/nRemote.jar")
It can be interesting to create a shortcut of "nRemote.jar" anywhere you want.


IV - Known Issues :
-------------------
* PC :
    Q1: nRemote is stuck on "FAILED TO INITIALIZE" and TI-Nspire Computer Software can't see my handhelds !
    R1: It appears you launched nRemote before launching TI-Nspire Computer Software. This leads to a strange behavior and can be resolved either by going to the Task Manager and killing java.exe/javaw.exe and TI-Nspire Computer Software, or by restarting Windows in extreme cases.

* Mac :
    Q2: The GUI may look flat with red dots.
    R2: There may be a Java version conflict (1.6/1.7). Open a Terminal window, try "java -jar [the nRemote.jar full path]".

* General :
    Q3: nRemote says (in its title) that one (or more) device is connected, but there is none.
	R3: Wait a little bit or use the refresh option in TI-Nspire Computer Software in order to manually remove or add handhelds from the global communication system. For example, the Navigator Wireless System has a window where you can see the connected devices. There, press the Refresh Button.
    Q4 : Some keys don't work.
    R4: Well, some keys like exp() don't work and we don't know yet how to solve that. It's not directly a bug related to nRemote.


V - Changelog :
---------------
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

Future :
- Internal Sequence Editor
- Internet control (to allow some kind of remote internet assistance)  -  Could be done soon, maybe idk
- Getting keypresses from the handheld to the computer ?  -  Done in a tricky way.


VI - License :
--------------
WTFPL License ( http://sam.zoy.org/wtfpl/ ). But also thank us. And visit http://tiplanet.org :)
