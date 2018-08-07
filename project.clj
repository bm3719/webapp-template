(defproject webapp-template "0.1.0-SNAPSHOT"
  :description "A barebones CLJ/CLJS web application template."
  :url "https://github.com/bm3719/webapp-template"
  :license {:name "GNU General Public License Version 3"
            :url "https://www.gnu.org/licenses/gpl-3.0.en.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]]
  :repl-options {:init-ns user}
  :global-vars {*print-length* 100}
  :deploy-branches ["master"]
  :profiles {:dev {:resource-paths ["dev"]
                   :dependencies [[org.clojure/tools.namespace "0.2.11"]]}}  )
