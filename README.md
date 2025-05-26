<p align="center">
  <img src="https://github.com/user-attachments/assets/4d4eafbf-9908-4a72-a9aa-9e93a7e5eb09" alt="Logo CoinquyLife" width="200" />
</p>

# 🏠 CoinquyLife – AndroidApp

**CoinquyLife** è una piattaforma web pensata per semplificare la vita in una casa condivisa. Ogni funzionalità è implementata come **microservizio indipendente**, seguendo l'architettura **Spring Boot + MVC**, e comunicano tra loro tramite API REST.

## 🌐 Versione Webapp

La versione web della piattaforma **CoinquyLife** è disponibile nel repository separato dedicato al progetto web.

**GitHub Repository Webapp:**  
[https://github.com/tuo-username/coinquylife-webapp](https://github.com/minicla03/CoinquyLifeSE)

---

## ⚙️ Funzionalità principali

- 🧹 **Gestione dei turni** – Crea e assegna turni di pulizia o attività.
- 💸 **Gestione delle spese** – Registra spese condivise e calcola bilanci.
- 📬 **Bacheca dei messaggi** – Invia e ricevi comunicazioni tra coinquilini.
- 📊 **Sondaggi** – Partecipa a decisioni comuni tramite votazioni.
- 🏆 **Classifica gamificata** – Guadagna punti per attività completate.
- 🏠 **Selezione della casa** – Crea o entra in una casa condivisa.

---

## 🧱 Struttura del progetto

- **Linguaggi**: Java/Kotlin
- **Framework**: Android SDK
- **Database**: Room (persistenza locale) e MongoDB (persistenza remota)
- **Stile Architettura**: Clean Archietecture combinata con MVVM (Model-View-ViewModel)

![image](https://github.com/user-attachments/assets/cb57acec-829c-4451-9a74-a20344f902aa)

 ### Architettura tecnica

- **MVVM**: Separation of concerns tra logica, UI e dati.
- **Room**: Database SQLite con DAO e repository.
- **LiveData**: Osservazione dati in tempo reale.
- **ViewModel**: Gestione dello stato delle UI in modo lifecycle-aware.
- **Navigation Component**: Gestione sicura delle transizioni tra fragment.


#### 📂 Livelli principali

- **Presentation Layer**  
  Contiene la UI e la logica di presentazione. Utilizza il pattern **MVVM** con `ViewModel`, `LiveData`.

- **Domain Layer**  
  Cuore dell'applicazione, indipendente da Android. Contiene:
  - **Use Case Interface**: Interfacce per DIP
  - **Use Case**: Logica di business dell'app
  - **Entity**: Modelli del dominio condivisi
  - **Repository Interface**: Interfacce per DIP

- **Data Layer**  
  Gestisce l’accesso ai dati. Comprende:
  - **Repository Implementation**: Implementazioni dei repository del dominio
  - **Room DAO**: Per la persistenza locale
  - **Convertes**: Per convertire i dati tra strati

---

## Design & UX

- **Librerie UI**: Material Design 3, Jetpack Components
- **Temi**: Modalità chiara/scura
- **Accessibilità**: Testo scalabile, contrasto elevato

---

## Requisiti

- **Android Studio**: Versione 2024.3.1 Patch 2 o superiore
- **Gradle**: Versione minima supportata dal progetto
- **SDK Android**: Min SDK 21, Target SDK 33


