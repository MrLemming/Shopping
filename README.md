# **Потоки ввода-вывода. Работа с файлами. Сериализация**

Перед Вами программа, планирующая продуктовую корзину.
Пользователь вводит номер продукта из первого списка и количество продуктов этого вида, которое хочет купить. Программа добавляет указанное количество выбранного продукта в итоговую сумму продуктовой корзины. Действие повторяется до тех пор, пока пользователь не введет команду “end”. После этого выводится вся корзина выбранных товаров и итоговая сумма.

В классе ```Basket``` представлены следующие методы:
* конструктор ```Basket(String[] products, int[] prices)``` , принимающий массив цен и названий продуктов;
* ```addToCart(int productNum, int amount)``` - метод добавления ```amount``` штук продукта номер ```productNum``` в корзину;
* ```printCart()``` - метод вывода на экран покупательской корзины;
* ```saveTxt(File textFile)``` - метод сохранения корзины в текстовый файл;
* ```static Basket loadFromTxtFile(File textFile)``` - статический метод восстановления объекта корзины из текстового файла, в который ранее была она сохранена;
* геттеры ```getProducts()```, ```getPrices()```, ```getProductBasket()```.

После ввода каждой покупки пользователем пользовательская корзина сохраняется в файл ```basket.txt```. При старте программа ищет этот файл в корне проекта и если он находится, восстанавливает корзину из него. Если файл не найдет - начинает с пустой корзины.

Также от ```main``` отведена ветка ```serial```, в которой корзина покупок сохраняется и восстанавливается через встроенную сериализацию в Java.

Для этого в классе ```Basket``` добавлены методы:

* ```saveBin(File file)``` - метод для сохранения в файл в бинарном формате;

* ```static loadFromBinFile(File file)```  - метод для загрузки корзины из бинарного файла.

В main текстовый файл для сохранения и загрузки заменен на бинарный ```basket.bin```.