# ADBSetting

1. 앱 실행
```
adb shell am start -a android.intent.action.MAIN -n 패키지명/클래스명
```

2. 앱 종료
```
adb shell am force-stop 패키지명
```

3. 파일 전송
```
adb push COPY_FOLDER// //sdcard//PASTE_FOLDER//
```

4. 파일 삭제
```
adb shell rm -rf REMOVE_PATH
```

5. 터치 입력
```
adb shell input tap POS_X POS_Y
```

6. 키 입력
```
adb shell input keyevent KEY_CODE
```




999. 기타
- monkeyrunner는 명령어처리가 adb에 비하여 느리다
- 자동화 스크립트를 작성하는 경우에, input 사이에 간격을 주어야 제대로 입력이 된다
