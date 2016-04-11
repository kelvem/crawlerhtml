INSERT INTO tag_rule (tag, parent_tag, text, style, clazz, percent)  
select tag, parent_tag, text, style, clazz, count(1) as percent 
from html_tag_1 
where text != ''
group by tag, parent_tag, text, style, clazz
having percent > 5
;

select clazz, count(1) as c from (
select tag, parent_tag, text, style, clazz, count(1) as cnt 
from html_tag_1 
where text != ''
group by tag, parent_tag, text, style, clazz
having cnt > 5
order by cnt desc ) t group by clazz order by c desc
;





