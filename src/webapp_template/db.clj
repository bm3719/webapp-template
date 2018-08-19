(ns webapp-template.db
  (:require [korma.core :as kc]
            [korma.db :as k]))

(k/defdb db (k/sqlite3 {:db "sqlite.db"}))

(declare person gender)

(kc/defentity person
  (kc/belongs-to gender))
(kc/defentity gender)

;;; Standard queries.

(defn table-list [table]
  (kc/select table))

(defn table-list-property [table k v]
  (kc/select table (kc/where {k v})))

(defn table-get-property [table k v]
  (first (kc/select table (kc/where {k v}))))

(defn table-get [table id]
  (table-get-property table :id id))

(defn table-insert [table entity]
  (kc/insert table (vals entity)))

(defn table-update [table entity id]
  (let [entity (dissoc entity :id)]
    (kc/update table
               (keys entity)
               (kc/where {:id id}))))

(defn table-delete [table id]
  (kc/delete table (kc/where {:id id})))

(defn table-check [table id]
  (first (kc/select table (kc/fields :id) (kc/where {:id id}))))

;;; Entity-aware queries.

(def entity-query
  (-> (kc/select* person) (kc/with gender)
      (kc/fields :id :first_name :last_name :nickname
                 [:gender.name :gender_name]
                 [:gender.description :gender_description])))

(defn select-person
  ([] (-> entity-query (kc/select)))
  ([id] (-> entity-query (kc/where (= :id id)) (kc/select))))
