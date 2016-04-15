# AudSplit
This tool is meant to be used to split a audio stream into smaller pieces given all time intervals.

The tool expects a `.m4a` audio stream and outputs a list of smaller `.m4a`  audio pieces
## Before Running
Modify `startup.yml`(Please look at `startup.yml.def` for how to modify `startup.yml`) according to the following instructions:
* Source: The full path of the audio file
* Output: The outout dir for the output files
* Format: Time format, M(_minutes_, in the format of `01:00`) or S(_seconds_, in the format of `60`)
* mp4box: The full path to mp4box executable
* muxer: The full path to lsmash muxer executable
* Time: The name for the output audio: Time range(in the foramt of `01:00-02:00` or `60-120`)

## Sample startup.yml
```yaml
Source: <source/dir>/audio.m4a
Output: <output/dir>
Format: M
Path:
    - mp4box: <path/to/mp4box>/mp4box.exe
    - muxer: <path/to/muxer>/muxer.exe
Time:
    - Dawn: 00:00-02:40
    - Stars and Butterflies: 2:40-04:42
    - The Living Sculptures of Pemberley: 04:42-7:45
    - Meryton Townhall: 07:45-9:00
    - The Militia Marches In: 09:00-09:58
    - Georgiana: 09:58-11:33
    - Arrival At Netherfield: 11:33-13:18
    - A Postcard to Henry Purcell: 13:18-16:00
    - Liz on Top of the World: 16:00-17:15
    - Leaving Netherfield: 17:15-19:05
    - Another Dance: 19:05-20:22
    - The Secret Life of Daydreams: 20:22-22:20
    - Darcy's Letter: 22:20-26:18
    - Can't Slow Down: 26:18-27:35
    - Your Hands Are Cold: 27:35-32:59
    - Mrs. Darcy: 32:59-36:37
    - Credits: 36:37-41:33
```

## How to Run it
1. `sbt clean assembly`
2. `java -jar target/scala-2.11/AudSplit.jar`

## Downloads
mp4box: https://gpac.wp.mines-telecom.fr/downloads/

lsmash: https://down.7086.in/vapoursynth/l-smash_r966.zip

## Others
`muxer` is not used for now, the path for muxer can be left as empty string
