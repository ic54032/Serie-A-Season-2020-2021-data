PGDMP     -                	    |           SerieATeams    15.2    15.2     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    51655    SerieATeams    DATABASE     �   CREATE DATABASE "SerieATeams" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Croatian_Croatia.1250';
    DROP DATABASE "SerieATeams";
                postgres    false            �            1259    51656    teams    TABLE     �  CREATE TABLE public.teams (
    team character varying(255),
    goals integer,
    shotspg double precision,
    yellow_cards integer,
    red_cards integer,
    avgposessionperc double precision,
    avgpassaccperc double precision,
    aerialswon double precision,
    rating double precision,
    playerfirstname character varying(255),
    playerlastname character varying(255),
    "position" character varying(255)
);
    DROP TABLE public.teams;
       public         heap    postgres    false            �          0    51656    teams 
   TABLE DATA           �   COPY public.teams (team, goals, shotspg, yellow_cards, red_cards, avgposessionperc, avgpassaccperc, aerialswon, rating, playerfirstname, playerlastname, "position") FROM stdin;
    public          postgres    false    214   �       �   �  x��X�R�H<_�8,Y/m�����D��^
�mKݎ��.��͒m,y�̅02�ʮ���yS�n*JS
�AJiB	�� �,�(�)d1�h6E������je�-���]�~�ƺ���4{Ѫ(�є��2��X&쬹�.+�N���B���9���U��,=X�Enieݿ��I���i<� (IhD�hS&?�]&^aŪ�G[X�UğaήV*\>X��/�/b���[)S�$���5��y�����˹�҈�RfC�(����`�no\Yc�5e�=5<�_��?�3���u���O�蒮4�΅�d8���S7|�-4e	�@�e��R ����M����jӫ�;)���z��q��.j@�����>�z�ge�p<�em�]�/h�c�_L�ec�+�xL!����j6x��{�e��e���Պ۶r��T���W7��s.���5����h���4��[�$�H�a$��� l�J�C�t�츶�j��t�^�kS>7E�����w�)��t�������G3���.���i�\Uv%��`,c����d��.$���,=6��ޙ�s�*��wY��mt�'��idJ����J��7גi�䒗�pR����i�B�@R�(��ivZ�Ɉ��ó�T���ě�{Ɏf'����?ɱ�-t�v�>S�Ұ�)�P}�p�;!R�+%�ߣA"�P�{iiR��h�ώ�U���T��}�L�c��K1��(_�&Z�j�lh�x(�R���ֱWM&
;f�TA~��M�~?�4��%��B�u>?�I��wc_ysV�'���@�~�N9
𤐳����LBc�H��R�ᰕO
UU�l�8U��b�ei=T�5����/~.���
[�7�/�y���Ru����hl�[�d�t$#��ұ4R����Ke��I����9��Z[g�;��>)VN�t՘%��I�768�cEG-���5��ۡ�"�u(�J�Q���]��I�T/g��{k���Iҧ?�r�݌��ozi�KKf;Z}A>�$#Ru����*EQxp4E�b
~���t��0�MU���7��zC7L1+����s�,���?�Ho�/�Ft��ƶξU��2ÕҘp�
�2��@���{N���6RWl{S�~?x�u'9�`�5\eki����?�&�Ww�+w����<VK�0f!;x&ki)�{�j��r	��ov���9�'e��������V��׎�r�zV�����jw�C��8��R3�d -�Xb�EH�aU݋I�h ��m�z�	����6��)*�1�:��N6B�(��`�^� �t,<��_2�]�{����%謱SQxW�� ��M����W����'~�mR�@� npE��5���a��q\/'u����h�X�-:��B@�q�1!M��0�r���	�Pf���v�� 9�(�83P�r.�{c@�a��(�ה�q,�� -����u���I�[�䶷-�9�R�\�:��|t�'����7�>�/�t��gpqq�?L�     