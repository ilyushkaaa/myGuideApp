package com.example.myguideapp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;

import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SightsActivity extends AppCompatActivity {
    private ListView lsSights;
    private Sight[] allSights;
    private User user;
    private String pathFile = "guideeee.bin";



    private void createSights(){
        allSights = new Sight[15];
        allSights[0] = new Sight("drawable1","Знаменский Кафедральный собор",51.7277610,
                36.1920290, "9:00-17:00",1816, "Кафедра́льный " +
                "собо́р Ико́ны Бо́жией Ма́тери «Зна́мение» (Зна́менский собо́р) — православный храм на " +
                "территории Курского Знаменского Богородицкого мужского монастыря в историческом " +
                "центре города Курска, построенный в 1816—1826 годы в честь победы в Отечественной " +
                "войне 1812 года. Храм был возведён в стиле классицизма, отразив в себе черты " +
                "западноевропейского Ренессанса, и имеет традиционную крестово-купольную " +
                "конструкцию, крестообразную в плане[1], со значительно удлинённой западной частью.");
        allSights[1] = new Sight("drawable2","Памятник курской антоновке",51.73375,
                36.1938194,
                "Круглосуточно",2004, "В 2004 году " +
                "уроженец Курской области знаменитый скульптор Вячеслав Клыков изготовил памятник " +
                "«Курская антоновка», посвящённый символу родной области (одна из последних его" +
                " работ). Курскпромбанк приобрел скульптуру для города Курска. Впоследствии правление " +
                "банка совместно с администрацией города согласовали место установки памятника-символа. " +
                "Этим местом стала площадь перед одним из отделений Курскпромбанка и " +
                "Воскресенско-Ильинским Храмом. Открытие монумента 19 августа 2008 года было " +
                "приурочено к православному празднику преображения Господня, в народе известному " +
                "под названием «Яблочный спас».");
        allSights[2] = new Sight("drawable3","Курский драматический театр", 51.7390470, 36.191531,
                "11:00-23:00",1792, "Курский" +
                " государственный драматический театр имени А.С. Пушкина — один из старейших " +
                "театров России, о снован в 1792 году стараниями просвещенного и делового " +
                "генерал-губернатора А.А. Беклешева. В 1805 году состоялся дебют на курской сцене " +
                "талантливого крепостного Михаила Щепкина, ставшего впоследствии великим русским " +
                "артистом. В 1937 году, в год 100-летия со дня смерти А.С. Пушкина, Курский театр" +
                " удостаивается чести носить имя великого поэта.");
        allSights[3] = new Sight("drawable4","Триумфальная арка",51.7873540, 36.1706740,
                "Круглосуточно",2000, "Триумфальная" +
                " арка в Курске была построена в 2000 году. Это первая арка в мире, посвященная" +
                " победе над фашизмом. В ее строительстве участвовали московские инженеры," +
                " петербургские литейцы, бетонщики с немецкого завода в курской Судже и атомщики " +
                "из Курчатова. Триумфальная арка входит в состав мемориального комплекса «Курская" +
                " дуга».");
        allSights[4] = new Sight("drawable5","Мемориальный комплекс Курская дугa", 51.7876770, 36.1697000,
                "Круглосуточно",1998, "Курская дуга" +
                " — мемориальный комплекс, расположенный вдоль проспекта Победы города Курска." +
                " Объекты комплекса служат цели увековечивания подвига советского народа в Курской " +
                "битве времён Великой Отечественной войны. Открытие состоялось к 55-летию Курской" +
                " битвы 5 июля 1998 года.");
        allSights[5] = new Sight("drawable6","Коренная пустынь", 51.97253, 36.31297,
                "9:00-19:00",1597, "Ку́рская " +
                "Коренна́я Рождества́ Пресвято́й " +
                "Богоро́дицы пу́стынь — мужской монастырь Курской епархии Русской православной " +
                "церкви, расположенный в местечке Свобода Золотухинского района Курской области. " +
                "Основан в 1597 году на месте явления Курской Коренной иконы. Древнее название " +
                "Курского Коренного монастыря — «Чёрная Коренная пустынь», что означает глухое, " +
                "запущенное место.");
        allSights[6] = new Sight("drawable7","Здание дворянского собрания",51.7260167, 36.191975,
                "9:00-21:00",1877, "" +
                "Зда́ние Дворя́нского собра́ния " +
                "— трёхэтажное кирпичное угловое здание в стиле эклектики, располагающееся в " +
                "историческом центре города Курска по адресу ул. Сонина, 4 на пересечении улиц " +
                "Сонина (ранее — Верхне-Набережной) и Ендовищенской, памятник архитектуры " +
                "федерального значения[");
        allSights[7] = new Sight("drawable8","Курский Краеведческий музей",51.72774300,36.19155600,
                "10:00-20:00",1905 , "" +
                "Курский областной краеведческий музей " +
                "(официально — областное бюджетное учреждение культуры «Курский областной " +
                "краеведческий музей», КОКМ) — историко-краеведческий музей, расположенный в " +
                "городе Курск. Музей открылся 31 января 1905 года; к 1915 году располагал" +
                " коллекцией из 10 тысяч экспонатов и библиотекой из 1000 единиц хранения.");
        allSights[8] = new Sight("drawable9","Памятник морякам АПРК «Курск»",51.7561640, 36.1845650,
                "Круглосуточно",2003, "На" +
                " монументе высечены имена погибших на атомном подводном ракетном крейсере «Курск» " +
                "в Баренцевом море. Спереди находится скульптура белого ангела. Памятник открыт и " +
                "освящен в 2003 году на Мемориале памяти павших. На входе в него есть следующие " +
                "слова на гранитных плитах: «Вечен ваш подвиг в сердцах поколений».");
        allSights[9] = new Sight("drawable10","Мемориал памяти павших 1941 -1945 гг.",51.755803, 36.184291,
                "Круглосуточно",2000, "" +
                "Центром мемориального комплекса выступает " +
                "обелиск Славы, рядом с ним горит Вечный огонь. К нему ведут стелы с изображениями" +
                " шлемов летчиков и танкистов, касками пехотинцев. На территории комплекса стоит " +
                "Памятник неизвестному солдату, братская могила с именами умерших в Курской битве. " +
                "Мемориал открыт в 2000 году на площади Героев Курской битвы. Комплекс построен на " +
                "бывших солдатских кладбищах");
        allSights[10] = new Sight("drawable11","Курский государственный цирк",51.7256920, 36.1892960,
                "11:00-19:00",1971, "Курский " +
                "государственный цирк — культурный " +
                "развлекательный центр в Курске. Торжественное открытие цирка состоялось в 1971" +
                " году. Проектная вместимость цирка 1200 мест. Курский цирк горел в 1990 году," +
                " но тогда печальных последствий удалось избежать. Первое предупреждение не было" +
                " услышано, и тогда через 6 лет — 14 декабря 1996 года — здание цирка сгорело " +
                "полностью. Новое здание цирка открыто 11 ноября 2011 года.");
        allSights[11] = new Sight("drawable12","Ветроэлектростанция Уфимцева",51.7341820, 36.1856540,
                "Круглосуточно",1931, "" +
                "Ветроэлектростанция Уфимцева — первая в мире ветроэлектрическая станция с " +
                "инерционным аккумулятором, первая в России ветряная электростанция, " +
                "построенная в 1931 году в г. Курске изобретателем А. Г. Уфимцевым. Первая в" +
                " мире ветроэлектростанция с накопителем энергии, старейшая сохранившаяся " +
                "ветроэлектростанция России.");
        allSights[12] = new Sight("drawable13","Храм Успения Пресвятой Богородицы", 51.7323140, 36.1878140,
                "8:00-17:00",1896,
                "Храм Успения Пресвятой Богородицы — " +
                        "католический храм в городе Курск. Административно относится к Архиепархии " +
                        "Матери Божией (с центром в Москве), возглавляемой архиепископом митрополитом " +
                        "Паоло Пецци. Расположен по адресу: ул. Марата, д. 31. В храме регулярно " +
                        "проводятся органные концерты.");
        allSights[13] = new Sight("drawable14","МБОУ СОШ №59", 51.733,36.136646, "" +
                "8:00 - 18:40", 1998, "Самое популярное место в мире. Не " +
                "нужно дополнительного описания");
        allSights[14] = new Sight("drawable15","Памятник курскому соловью", 51.760954, 36.185664,
                "Круглосуточно", 2016, "Скульптура представляет собой " +
                "поющую бронзовую птицу, расположенную на полукруглом лавровом венце, в основании " +
                "памятника надпись — «Курский соловей». Птица по праву считается живым символом " +
                "края. Песня курского соловья насчитывает от 8 до 24 колен: чистейшие звуки флейты," +
                " щелканье, клекот, посвист и множество других вариаций. Поют только самцы, так они" +
                " привлекают будущую супругу. Курского соловья часто упоминают в песнях, стихах и прозе.");
       /* allSights[15] = new Sight("drawable15", "ddddddd", 37.3394, -121.895,
                "ffff", 2222, "hgfdcvghgfghjhgfvghjhg");*/
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);
        Intent intent = getIntent();

        createSights();
        user = (User)intent.getSerializableExtra("user");
        showSights();
        androidx.cardview.widget.CardView card = findViewById(R.id.card2);
        GradientDrawable border = new GradientDrawable();

        border.setStroke(1, Color.parseColor("#292726")); // толщина и цвет границы
        border.setCornerRadius(20);
        card.setBackground(border);


        TextView textHello = findViewById(R.id.helloText);
        textHello.setText(textHello.getText() + " " + user.getName() + " " + user.getSurname());

    }
    public void onMenuClick(View v){
        CardView buttonMenu = findViewById(R.id.buttonmenu);
        buttonMenu.setOnClickListener(view -> {
            PopupMenu menu = new PopupMenu(SightsActivity.this, buttonMenu);
            menu.getMenuInflater().inflate(R.menu.menu_favour, menu.getMenu());
            menu.show();
            menu.setOnMenuItemClickListener(menuItem -> {
                if (menuItem.getItemId() == R.id.menu_item1) {
                    Sight[] arr = user.getFavourSights().toArray(new Sight[0]);
                    Intent intent1 = new Intent(SightsActivity.this,
                            FavourActivity.class);
                    intent1.putExtra("user", user);
                    intent1.putExtra("favsights", arr);
                    intent1.putExtra("allsights", allSights);

                    startActivity(intent1);
                }
                else{
                    finishAffinity();
                    Intent intent2 = new Intent(SightsActivity.this,
                            MainActivity.class);
                    startActivity(intent2);
                }
                return true;
            });
            //Log.d("sss", "click");

        });
    }
    private void showSights(){
        List<String> sightNames = new ArrayList<>();
        for (int i = 0; i < allSights.length; ++i){
            sightNames.add(allSights[i].getName());
        }
        List<String> favSightNames = new ArrayList<>();
        for (int j = 0; j < user.getFavourSights().size(); ++j){
            favSightNames.add(user.getFavourSights().get(j).getName());
        }
        lsSights = findViewById(R.id.sightsList);
        ListAdapter arrayAdapter= new ListAdapter(this,
                R.layout.list_sights, sightNames, favSightNames);
        lsSights.setAdapter(arrayAdapter);

    }
    public void onCheckClick(View view){
        int pos = lsSights.getPositionForView(view);
        if (user.getFavourSights().contains(allSights[pos])){
            user.getFavourSights().remove(allSights[pos]);
        }
        else {
            user.addSight(allSights[pos]);
            SessionInfo.getAllUsers().remove(findUser());
            SessionInfo.addUser(user);
        }
    }
    public int findUser(){
        for (int i = 0; i < SessionInfo.getAllUsers().size(); ++i){
            if(SessionInfo.getAllUsers().get(i).getName().equals(user.getName())
            && SessionInfo.getAllUsers().get(i).getSurname().equals(user.getSurname())
            && SessionInfo.getAllUsers().get(i).getEmail().equals(user.getEmail())){
                return i;
            }
        }
        return -1;
    }
    public void onSightClick(View view){
        int pos = lsSights.getPositionForView(view);
        Sight currentSight = allSights[pos];
        Intent intent = new Intent(SightsActivity.this, MapActivity.class);
        intent.putExtra("sight", currentSight);
        intent.putExtra("user", user);
        intent.putExtra("allsights", allSights);
        startActivity(intent);
        //finish();
    }
    private void saveInfo() throws IOException {
        FileOutputStream fos = openFileOutput(pathFile, Context.MODE_PRIVATE);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeInt(SessionInfo.getAllUsers().size());
        for (User us: SessionInfo.getAllUsers()){
            oos.writeObject(us);
        }
        oos.close();
        fos.close();
    }
    @Override
    protected void onPause() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onPause();
    }
    @Override
    protected void onStop() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onStop();
    }
    @Override
    protected void onDestroy() {
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void onRestart(){
        super.onRestart();
        try {
            saveInfo();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
