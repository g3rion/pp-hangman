# Podstawy programowania - projekt zaliczeniowy

# 2. Gra w wisielca

## Opis
Wisielca nie trzeba nikomu przedstawiać, ale na wszelki wypadek - program po wyborze poziomu trudności, losuje nam słowo które gracz ma za zadanie zgadnąć, podająć literę po literze. Wszystkie odpowiedzi będą zapisywane, jednak poprawne wylądują w odpowiednim miejscu w słowie, a niepoprawne "dorysują" element naszego wisielca. Elementy odpowiadają wykorzystanej ilości "żyć". Jeśli nie uda nam się zgadnąć słowa wystarczająco szybko, wisielec zostanie narysowany w całości, a gra zakończy się porażką. Wygrana następuje po odgadnięciu całego słowa, zanim wisielec zostanie ukończony. W tej, anglojęzycznej wersji gry można również edytować bazę słów oraz przeglądać statystyki rozgrywki.

## Funkcjonalności
- **Wybór losowego słowa**: Program wybiera losowe słowo z bazy słów.
- **Edytowanie bazy danych**: Możliwość dodawania, usuwania i przeglądania słów w bazie.
- **Wybór poziomu trudności**: Możliwość wyboru poziomu trudności (łatwy, średni, trudny). Wyższy poziom oznacza dłuższe słowa losowane z 
- **Dostęp do statystyk**: Możliwość przeglądania statystyk rozgrywki, takich jak liczba wygranych, przegranych, procent wygranych oraz średnia liczba błędów przed wygraną.
- **Automatyczne wypełnianie znaków specjalnych**: Automatyczne wypełnianie spacji i znaków specjalnych w słowie, aby gracz musiał zgadywać tylko litery.

## Instrukcja uruchomienia
### Wymagania
- Java Development Kit (JDK) w wersji 8 lub nowszej.

### Instalacja
1. **Pobierz kod źródłowy**:
   Skopiuj kod źródłowy do pliku `Hangman.java`.

2. **Utwórz plik z bazą słów**:
   Utwórz plik tekstowy `words.txt` w tym samym katalogu co plik `Hangman.java` i dodaj do niego słowa, po jednym słowie na linię. Przykładowa baza danych z postaciami, jak i lokacjami z uniwersum Władcy Pierścieni, Gwiezdnych Wojen i Harrego Pottera znajduje się już w repozytorium.

3. **Kompilacja programu**:
   Otwórz terminal lub wiersz poleceń, przejdź do katalogu z plikiem `Hangman.java` i wykonaj komendę:
   ```bash
   javac Hangman.java

#### Edytowanie Bazy Danych
Aby edytować bazę danych, wybierz opcję "2. Edit Word Database" i wybierz pożądaną opcję po numerze.

#### Przeglądanie Statystyk
Wybierz opcję "3. View Statistics" z menu głównego, aby zobaczyć statystyki rozgrywki.