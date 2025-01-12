# CryptoCompare

Мобильное приложение для отслеживания курсов криптовалют в реальном времени.

## Описание

CryptoCompare - это Android-приложение, которое позволяет пользователям:
- Отслеживать актуальные курсы топ-20 криптовалют
- Просматривать детальную информацию о каждой криптовалюте
- Получать обновления цен каждые 10 секунд
- Работать с приложением как в портретном, так и в ландшафтном режиме

## Технологии

- Kotlin
- Clean Architecture
- MVVM
- Dagger 2
- Room
- Retrofit 2
- WorkManager
- Picasso
- ViewBinding
- LiveData
- Coroutines

## Особенности архитектуры

- Clean Architecture с разделением на слои data, domain и presentation
- Паттерн Repository для абстракции источников данных
- Dependency Injection с использованием Dagger 2
- Реактивное обновление UI с помощью LiveData
- Фоновая синхронизация данных через WorkManager
- Поддержка различных конфигураций экрана (портретная/ландшафтная)

## API

Приложение использует [CryptoCompare API](https://min-api.cryptocompare.com/) для получения актуальных данных о криптовалютах.

## Установка

1. Склонируйте репозиторий: bash
git clone https://github.com/your-username/CryptoCompare.git

2. Откройте проект в Android Studio

3. Запустите приложение на эмуляторе или реальном устройстве

## Требования

- Android Studio Arctic Fox или новее
- Минимальная версия SDK: 24
- Целевая версия SDK: 34
