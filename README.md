# Egzaminas_2022

Aplikacija programavimo egzaminui

## Prisijungimo duomenys:

Administratorius:

- Naudotojo vardas: admin
- Slaptažodis: admin

Naudotojas:

- Naudotojo vardas: user
- Slaptažodis: user

## Pradėti

- Nusikklonuoti repozitoriją `https://github.com/ModestasNorbutas/programavimo-egzaminas-2022`

### Pasileisti Tomcat serveryje

- nueiti į projekto aplanką `cd .../programavimo-egzaminas-2022/back`
- pasileisti aplikaciją Tomcat serveryje (port 8081):

```
 mvn clean install org.codehaus.cargo:cargo-maven2-plugin:1.7.7:run -Dcargo.maven.containerId=tomcat9x -Dcargo.servlet.port=8081 -Dcargo.maven.containerUrl=https://repo1.maven.org/maven2/org/apache/tomcat/tomcat/9.0.40/tomcat-9.0.40.zip
```

- aplikacija pasileis naršyklėje http://localhost:8081/app

### Pasileisti su Spring boot ir npm/yarn

- nueiti į projekto aplanką `cd .../programavimo-egzaminas-2022/back`
- pasileisti `mvn spring-boot:run` (aplikacija pasileis ant port 8080)
- nueiti į aplanką `cd .../programavimo-egzaminas-2022/front`
- pasileisti `npm install` arba `yarn install`
- atsidaryti failą `..\programavimo-egzaminas-2022\front\src\components\Services\endpoint.js`
- pakeisti `const apiEndpoint= process.env.PUBLIC_URL` į `const apiEndpoint = "http://localhost:8080"`
- pasileisti `npm run start` arba `yarn start`
- aplikacija atsidarys naršyklėje http://localhost:3000

## Deployment

To make a war file for deployment:

- run `yarn build` while in the project folder `.../programavimo-egzaminas-2022/front`
- move all files from folder `.../programavimo-egzaminas-2022/front/build`
  to `.../back/source/main/resources/public`
- run `mvn clean install` while in the project folder `.../programavimo-egzaminas-2022/back`
- `app.war` file will appear in the `..\programavimo-egzaminas-2022\back\target` folder
