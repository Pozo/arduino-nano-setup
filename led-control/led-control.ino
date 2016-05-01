#define ON "ON"
#define OFF "OFF"

// the setup function runs once when you press reset or power the board
void setup() {
  // initialize digital pin 13 as an output.
  pinMode(13, OUTPUT);

  Serial.begin(9600);
  Serial.setTimeout(50);

  Serial.println("loaded");
  Serial.flush();
}
String command = "";

// the loop function runs over and over again forever
void loop() {
  if (Serial.available() > 0) {
    // read the incoming byte:
    command = Serial.readString();
    Serial.println(command + " ACK");
    Serial.flush();
    command.trim();

    if (command.equals(ON)) {
      digitalWrite(13, HIGH);
    } else if (command.equals(OFF)) {
      digitalWrite(13, LOW);
    }
  }
}
