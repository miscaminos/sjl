```python
import mediapipe as mp
import cv2
```

### hand detection trial-1


```python
mpHands = mp.solutions.hands
my_hands = mpHands.Hands()
mpDraw = mp.solutions.drawing_utils

cap = cv2.VideoCapture('C:/LSJ/0.FinalProject/Aihub_data_videos/KETI_SL_0000000002.avi')
```


```python
while True:
    success, img = cap.read()
    imageRGB = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
    results = my_hands.process(imageRGB)
    
    print(results.multi_hand_landmarks)
    
    if results.multi_hand_landmarks:
        for hand in results.multi_hand_landmarks:
            for idx, mark in enumerate(hand.landmark):
                h, w, c = img.shape
                cx, cy = int(mark.x*w), int(mark.y*h)
                print(idx, ":", cx, cy)
                if idx ==0:
                    cv2.circle(img, (cx,cy), 20, (255,0,0), cv2.FILLED)
                    
            mpDraw.draw_landmarks(img, hand, mpHands.HAND_CONNECTIONS)
 
    cv2.imshow("Gotcha", img)
    cv2.waitKey(1)
```

### hand detection trial-2


```python
mp_drawing = mp.solutions.drawing_utils
mp_hands = mp.solutions.hands

cap = cv2.VideoCapture('C:/LSJ/0.FinalProject/Aihub_data_videos/KETI_SL_0000000002.avi')
```


```python
with mp_hands.Hands(min_detection_confidence=0.5, min_tracking_confidence=0.5) as hands:
    while cap.isOpened():
        success, image = cap.read()
        if not success:
            print("Ignoring empty camera frame.")
            # If loading a video, use 'break' instead of 'continue'.
            break

        # Flip the image horizontally for a later selfie-view display, and convert
        # the BGR image to RGB.
        image = cv2.cvtColor(cv2.flip(image, 1), cv2.COLOR_BGR2RGB)
        # To improve performance, optionally mark the image as not writeable to
        # pass by reference.
        image.flags.writeable = False
        results = hands.process(image)
        print(results.multi_hand_landmarks)

        # Draw the hand annotations on the image.
        image.flags.writeable = True
        image = cv2.cvtColor(image, cv2.COLOR_RGB2BGR)
        if results.multi_hand_landmarks:
            for hand_landmarks in results.multi_hand_landmarks:
                mp_drawing.draw_landmarks(image, hand_landmarks, mp_hands.HAND_CONNECTIONS)
        cv2.imshow('MediaPipe Hands', image)
        
        if cv2.waitKey(10) & 0xFF == ord('q'):
            break
#         if cv2.waitKey(5) & 0xFF == 27:
#             break
            
cap.release()
```
