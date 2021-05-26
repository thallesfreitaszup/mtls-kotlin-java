CLIENT_KEYSTORE_DIR=src/main/resources


# Generate a client and server RSA 2048 key pair
keytool -genkeypair -alias server -keyalg RSA -keysize 2048 -keypass changeme -keystore keystore.jks -storepass changeme

keytool -genkeypair -alias client -keyalg RSA -keysize 2048 -keypass changeme -keystore invalid-clientstore.jks -storepass changeme

# Export public certificates for both the client and server
keytool -exportcert -alias server -file server-public.cer -keystore keystore.jks -storepass changeme

keytool -v -list -keystore keystore.jks