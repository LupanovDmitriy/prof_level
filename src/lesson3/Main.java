package lesson3;
/*
1. Добавить в сетевой чат запись локальной истории в текстовый файл на клиенте.
        Для каждой учетной записи файл с историей должен называться history_[login].txt.
        (Например, history_login1.txt, history_user111.txt)
        2. ** После загрузки клиента показывать ему последние 100 строк истории чата.
*/


import java.io.*;

public class Main {
    /* !!!!!!!!!!!!!!!!!!!!!!!!!!!!
    Так как я безнадежно отстал и у меня нет последнего рабочего варианта чата, делаю работу с фалами в пустом классе
    !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*/
    public static void main(String[] args) throws IOException {
        String lineSeparator = System.getProperty("line.separator");
        for (int i = 1; i <= 3; i++) {

            File file = new File("src/lesson3/1/history" + i + ".txt");
            if (file.exists()) {
                file.delete();
            }
            try (FileWriter out = new FileWriter(file, true)) {
                for (int j = 1; j < 180; j++) {
                    out.write(("forUser" + i + " Это сообщение номер " + j + lineSeparator));
                }
            }
        }
        showHistory(2,100);
    }

    public static int countLine(int i) throws IOException {
        File file = new File("src/lesson3/1/history" + i + ".txt");
        if (!file.exists()) {
            return 0;
        }
        int countLine = 0;
        try (BufferedReader in = new BufferedReader(new FileReader(file));) {
            while ((in.readLine()) != null) {
                countLine++;
            }
        }
        /* ПРОКОММЕНТИРЙУТЕ ПОЖАЛУЙСТА!!!
        Вывел в отдельный метод подсчет количества строк файла, так как не понял можно ли чистать поток сначала.
        Речь идет о том, что я в буфер забрал по сути текст и когда я читаю его по строкам, у него указатель стоит уже на новой строке
        после того как я его весь прошел и понял сколько в нем строк, я хочу сбросить указатель на самое начало буфера и пробежатся по нему снова
        Сейчас я по сути читаю его второй раз отдельным методом. думаю что тут можно как то сделать проще.

        в описании нашел команды mark() и reset() Марк я так понял это метка, на которую возвращается ресет.
        Но в качестве параметра туда передается буфер, который мне не известен...

        Подскажите, как бы выглядел правильный код по сбросу указателя на начало буфера.

         */

        return countLine;
    }

    public static void showHistory(int i,int count) throws IOException {
        File file = new File("src/lesson3/1/history" + i + ".txt");
        if (!file.exists()) {
            System.out.println("Нет истории данного пользователя");
            return;
        }
        int countLine=countLine(i);
        if (countLine==0){
            System.out.println("Нет истории данного пользователя");
        }
        else{
            System.out.printf("В истории пользователя %d сообщений \n",countLine);
        }

        int startLine=Math.max(0,countLine-count);
        int nextline=0;
        try(BufferedReader in =new BufferedReader(new FileReader(file));){
            String line="";
            while ((line=in.readLine())!=null){
                nextline++;
                if (nextline>=startLine){
                    System.out.println(line);
                }
            }
        }
    }

}
