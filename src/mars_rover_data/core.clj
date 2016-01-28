(ns mars-rover-data.core
  (:require [clj-http.client :as client]))


(def api-key "Tb7Lyf4DDzPqVbvGPY0WDzJ4fH0YPsEU05k7JaMm")
(def base-url (str "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos/?api_key=" api-key))

(defn pprint-output [data]
  (spit "resources/fhaz-imgs.clj" (with-out-str (clojure.pprint/pprint data))))

(defn add-param [k v]
  (str "&" (name k) "=" v))

(defn generate-url
  ([] (generate-url {}))
  ([params]
   (reduce (fn [s [k v]] (str s (add-param k v))) base-url params)))

(defn get-rover-data [url]
  (client/get url {:accept :json
                   :as :json
                   :throw-exceptions false}))

(defn get-pics [req-bodies]
  (map :img_src (mapcat #(get-in % [:body :photos]) req-bodies)))

(defn get-fhaz-data [sols]
   (filter #(= (:status %) 200)
           (pmap #(get-rover-data (generate-url {:camera "fhaz" :sol %})) sols)))
