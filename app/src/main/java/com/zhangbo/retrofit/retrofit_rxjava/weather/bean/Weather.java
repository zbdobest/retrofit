package com.zhangbo.retrofit.retrofit_rxjava.weather.bean;

import java.util.List;

/**
 * author : zhangbo
 * e-mail : zwill2014@163.com
 * date   : 2019/2/19 17:06
 * desc   :
 * version: 1.0
 */
public class Weather{
    private InnerWeather data;

    public InnerWeather getData() {
        return data;
    }

    public void setData(InnerWeather data) {
        this.data = data;
    }

    public static class InnerWeather {

        private List<NearestWeather> weather;

        public List<NearestWeather> getWeather() {
            return weather;
        }

        public void setWeather(List<NearestWeather> weather) {
            this.weather = weather;
        }

        public static class NearestWeather {

            /**
             * date : 2018-10-26
             * info : {"dawn":["7","小雨","16","东风","微风","17:13"],"day":["1","多云","22","西北风","3-5级","06:03"],"night":["0","晴","13","西北风","5-6级","17:12"]}
             * week : 五
             * nongli : 九月十八
             */

            private String date;

            private InfoBean info;

            private String week;

            private String nongli;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public InfoBean getInfo() {
                return info;
            }

            public void setInfo(InfoBean info) {
                this.info = info;
            }

            public String getWeek() {
                return week;
            }

            public void setWeek(String week) {
                this.week = week;
            }

            public String getNongli() {
                return nongli;
            }

            public void setNongli(String nongli) {
                this.nongli = nongli;
            }



            public static class InfoBean {

                private List<String> dawn;

                private List<String> day;

                private List<String> night;

                public List<String> getDawn() {
                    return dawn;
                }

                public void setDawn(List<String> dawn) {
                    this.dawn = dawn;
                }

                public List<String> getDay() {
                    return day;
                }

                public void setDay(List<String> day) {
                    this.day = day;
                }

                public List<String> getNight() {
                    return night;
                }

                public void setNight(List<String> night) {
                    this.night = night;
                }

                @Override
                public String toString() {
                    return "InfoBean{" +
                            "dawn=" + dawn +
                            ", day=" + day +
                            ", night=" + night +
                            '}';
                }
            }

            @Override
            public String toString() {
                return "NearestWeather{" +
                        "date='" + date + '\'' +
                        ", info=" + info +
                        ", week='" + week + '\'' +
                        ", nongli='" + nongli + '\'' +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "InnerWeather{" +
                    "weather=" + weather +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "data=" + data +
                '}';
    }
}
